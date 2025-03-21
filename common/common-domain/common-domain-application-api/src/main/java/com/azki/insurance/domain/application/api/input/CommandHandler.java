package com.azki.insurance.domain.application.api.input;

import com.azki.insurance.domain.application.api.command.Command;
import com.azki.insurance.domain.application.api.command.CommandResult;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface CommandHandler<C extends Command, R extends CommandResult<?>> {

    R handle(@Valid C command);
}
