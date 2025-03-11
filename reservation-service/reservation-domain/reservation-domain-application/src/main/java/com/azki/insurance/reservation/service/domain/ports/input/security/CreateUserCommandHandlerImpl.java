package com.azki.insurance.reservation.service.domain.ports.input.security;

import com.azki.insurance.common.app.config.CommonConfigData;
import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.SecurityHelper;
import com.azki.insurance.common.utility.SnowFlakeUniqueIDGenerator;
import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.CreateUserCommand;
import com.azki.insurance.reservation.service.domain.api.dto.UserDTO;
import com.azki.insurance.reservation.service.domain.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.api.query.UserCriteriaDTO;
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
public class CreateUserCommandHandlerImpl extends BaseCommandHandler<CreateUserCommand, CommandResult<UserDTO>> {

    private final UserRepository userRepository;
    private final CommonConfigData configData;

    @Override
    protected CommandResult<UserDTO> execute(CreateUserCommand command) {

        UserCriteriaDTO criteria = new UserCriteriaDTO();
        criteria.setUsernameEquals(command.getUserName());

        boolean exists = userRepository.exists(criteria);

        if (exists) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DUPLICATE_DATA,
                    "User already exists",
                    "User")
            ));
        }

        long userId = SnowFlakeUniqueIDGenerator.getGenerator(configData.nodeId()).nextId();

        UserDTO user = UserDTO.builder()
                .userId(userId)
                .username(command.getUserName())
                .password(SecurityHelper.generateUserPassword(command.getUserName(), command.getPassword()))
                .email(command.getEmail())
                .build();

        user = userRepository.save(user);

        CommandResult<UserDTO> commandResult = new CommandResult<>();
        commandResult.setData(user);

        return commandResult;
    }
}
