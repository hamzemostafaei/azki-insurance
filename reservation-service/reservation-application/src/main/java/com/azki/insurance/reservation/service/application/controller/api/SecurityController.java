package com.azki.insurance.reservation.service.application.controller.api;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.input.CommandHandler;
import com.azki.insurance.reservation.service.application.api.data.security.AuthenticationEdgeRequestDTO;
import com.azki.insurance.reservation.service.application.api.data.security.AuthenticationEdgeResponseDTO;
import com.azki.insurance.reservation.service.application.api.data.security.CreateUserEdgeRequestDTO;
import com.azki.insurance.reservation.service.application.api.data.security.CreateUserEdgeResponseDTO;
import com.azki.insurance.reservation.service.domain.api.command.CreateUserCommand;
import com.azki.insurance.reservation.service.domain.api.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security")
public class SecurityController {

    private final CommandHandler<CreateUserCommand, CommandResult<UserDTO>> createUserHandler;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationEdgeResponseDTO> login(@RequestBody AuthenticationEdgeRequestDTO edgeRequest) {

        AuthenticationEdgeResponseDTO response = new AuthenticationEdgeResponseDTO();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserEdgeResponseDTO> createUser(@RequestBody CreateUserEdgeRequestDTO edgeRequest) {
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
