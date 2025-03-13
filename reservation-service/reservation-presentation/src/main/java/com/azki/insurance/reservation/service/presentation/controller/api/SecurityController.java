package com.azki.insurance.reservation.service.presentation.controller.api;

import com.azki.insurance.domain.application.api.command.CommandResult;
import com.azki.insurance.domain.application.api.input.CommandHandler;
import com.azki.insurance.reservation.service.presentation.api.data.security.AuthenticationEdgeRequestDTO;
import com.azki.insurance.reservation.service.presentation.api.data.security.AuthenticationEdgeResponseDTO;
import com.azki.insurance.reservation.service.presentation.api.data.security.CreateUserEdgeRequestDTO;
import com.azki.insurance.reservation.service.presentation.api.data.security.CreateUserEdgeResponseDTO;
import com.azki.insurance.reservation.service.domain.application.api.command.CreateUserCommand;
import com.azki.insurance.reservation.service.domain.application.api.command.LoginCommand;
import com.azki.insurance.domain.application.api.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security")
public class SecurityController {

    private final CommandHandler<CreateUserCommand, CommandResult<UserDTO>> createUserHandler;
    private final CommandHandler<LoginCommand, CommandResult<UserDTO>> loginHandler;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationEdgeResponseDTO> login(@RequestBody AuthenticationEdgeRequestDTO edgeRequest, HttpServletRequest servletRequest) {

        AuthenticationEdgeResponseDTO response = new AuthenticationEdgeResponseDTO();

        CommandResult<UserDTO> commandResult = loginHandler.handle(LoginCommand.builder()
                .userName(edgeRequest.getUserName())
                .password(edgeRequest.getPassword())
                .build());

        if (commandResult.isSuccessful()) {
            response.setMessage("Successfully logged in");
            UserDTO user = commandResult.getData();
            UsernamePasswordAuthenticationToken authenticated =
                    UsernamePasswordAuthenticationToken.authenticated(user, user.getUsername(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticated);

            HttpSession session = servletRequest.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserEdgeResponseDTO> createUser(@Valid @RequestBody CreateUserEdgeRequestDTO edgeRequest) {
        CreateUserEdgeResponseDTO response = new CreateUserEdgeResponseDTO();

        CreateUserCommand command = new CreateUserCommand();
        command.setUserName(edgeRequest.getUserName());
        command.setPassword(edgeRequest.getPassword());
        command.setEmail(edgeRequest.getEmail());
        CommandResult<UserDTO> result = createUserHandler.handle(command);

        response.setUserName(result.getData().getUsername());
        response.setEmail(result.getData().getEmail());

        return ResponseEntity.ok(response);
    }

}
