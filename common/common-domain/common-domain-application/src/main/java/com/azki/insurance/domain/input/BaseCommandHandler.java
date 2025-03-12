package com.azki.insurance.domain.input;

import com.azki.insurance.domain.api.command.Command;
import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.input.CommandHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseCommandHandler<C extends Command, R extends CommandResult<?>> implements CommandHandler<C, R> {

    @Override
    public final R handle(C command) {

        if (log.isDebugEnabled()) {
            log.debug("Handling command [{}]", command);
        }

        R result = execute(command);

        if (result != null) {
            result.setSuccessful(true);
        }

        if (log.isDebugEnabled()) {
            log.debug("Handling command [{}] completed", command);
        }

        return result;
    }

    protected abstract R execute(C command);
}