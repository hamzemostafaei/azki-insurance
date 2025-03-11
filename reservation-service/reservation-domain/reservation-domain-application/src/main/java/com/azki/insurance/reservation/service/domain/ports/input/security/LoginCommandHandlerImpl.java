package com.azki.insurance.reservation.service.domain.ports.input.security;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.LoginCommand;
import com.azki.insurance.reservation.service.domain.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class LoginCommandHandlerImpl extends BaseCommandHandler<LoginCommand, CommandResult<Void>> {

    private final UserRepository userRepository;

    @Override
    protected CommandResult<Void> execute(LoginCommand command) {

        return null;
    }
}
