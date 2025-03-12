package com.azki.insurance.reservation.service.domain.ports.input.security;

import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.SecurityHelper;
import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.LoginCommand;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.reservation.service.domain.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.api.dto.search.UserCriteriaDTO;
import com.azki.insurance.reservation.service.domain.ports.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class LoginCommandHandlerImpl extends BaseCommandHandler<LoginCommand, CommandResult<UserDTO>> {

    private final UserRepository userRepository;

    @Override
    protected CommandResult<UserDTO> execute(LoginCommand command) {

        UserCriteriaDTO userNameCriteria = new UserCriteriaDTO();
        userNameCriteria.setUsernameEquals(command.getUserName());

        UserDTO user = userRepository.getSingleResult(userNameCriteria);
        if (user == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.SECURITY_ERROR,
                    "User name or password is incorrect.",
                    "Security"
            )));
        }

        if (!SecurityHelper.validateUserPassword(user.getUsername(), command.getPassword(), user.getPassword())) {
            if (log.isWarnEnabled()) {
                log.warn("User password is not valid: {}", user.getUsername());
            }

            return CommandResult.failure(new ErrorDTO(ErrorCodeEnum.SECURITY_ERROR, "User name or password is incorrect.", "Security"));
        }

        return CommandResult.success(user);
    }
}
