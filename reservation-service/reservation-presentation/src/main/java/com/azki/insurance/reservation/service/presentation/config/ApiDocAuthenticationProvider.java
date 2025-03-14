package com.azki.insurance.reservation.service.presentation.config;

import com.azki.insurance.domain.application.api.command.CommandResult;
import com.azki.insurance.domain.application.api.dto.UserDTO;
import com.azki.insurance.domain.application.api.input.CommandHandler;
import com.azki.insurance.reservation.service.domain.application.api.command.AuthenticationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiDocAuthenticationProvider implements AuthenticationProvider {

    private final CommandHandler<AuthenticationCommand, CommandResult<UserDTO>> authenticationHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            String userName = authentication.getName();
            String password = authentication.getCredentials().toString();
            CommandResult<UserDTO> commandResult = authenticationHandler.handle(AuthenticationCommand.builder()
                    .userName(userName)
                    .password(password)
                    .build());

            if (commandResult.isSuccessful()) {
                return SecurityContextHolder.getContext().getAuthentication();
            } else {
                throw new BadCredentialsException("Username or password is incorrect");
            }
        } else {
            throw new AuthenticationServiceException("Authentication Failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
