package com.azki.insurance.reservation.service.domain.application.security.input;

import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.SecurityHelper;
import com.azki.insurance.domain.application.api.command.CommandResult;
import com.azki.insurance.domain.application.api.dto.UserDTO;
import com.azki.insurance.domain.application.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.application.api.command.CreateUserCommand;
import com.azki.insurance.reservation.service.domain.application.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.application.security.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateUserCommandHandlerImpl extends BaseCommandHandler<CreateUserCommand, CommandResult<UserDTO>> {

    private final UserRepository userRepository;

    @Override
    protected CommandResult<UserDTO> execute(CreateUserCommand command) {

        validateUserUniqueness(command);

        UserDTO newUser = createUser(command);
        newUser = userRepository.save(newUser);

        return CommandResult.success(newUser);
    }

    private void validateUserUniqueness(CreateUserCommand command) {
        Boolean exists = userRepository.existsByUsernameOrEmail(command.getUserName(), command.getEmail());
        if (exists) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DUPLICATE_DATA,
                    "User already exists",
                    "User"
            )));
        }
    }

    private UserDTO createUser(CreateUserCommand command) {

        return UserDTO.builder()
                .userId(userRepository.getNextUserId())
                .username(command.getUserName())
                .password(SecurityHelper.generateUserPassword(command.getUserName(), command.getPassword()))
                .email(command.getEmail())
                .build();
    }
}
