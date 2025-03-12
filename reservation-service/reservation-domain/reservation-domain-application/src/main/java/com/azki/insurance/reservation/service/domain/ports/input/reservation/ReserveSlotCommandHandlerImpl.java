package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.common.app.config.CommonConfigData;
import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.SnowFlakeUniqueIDGenerator;
import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.ReserveSlotCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.ReservedSlotsDTO;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.UserCriteriaDTO;
import com.azki.insurance.reservation.service.domain.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import com.azki.insurance.reservation.service.domain.ports.output.ReservedSlotsRepository;
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
public class ReserveSlotCommandHandlerImpl extends BaseCommandHandler<ReserveSlotCommand, CommandResult<Void>> {

    private final AvailableSlotsRepository availableSlotsRepository;
    private final UserRepository userRepository;
    private final ReservedSlotsRepository reservedSlotsRepository;
    private final CommonConfigData commonConfigData;

    @Override
    protected CommandResult<Void> execute(ReserveSlotCommand command) {

        UserDTO user = validateAndGetUser(command);
        AvailableSlotsDTO slot = validateAndGetAvailableSlot(command);

        reserveSlot(user, slot);

        return CommandResult.success();
    }

    private void reserveSlot(UserDTO user, AvailableSlotsDTO slot) {
        long id = SnowFlakeUniqueIDGenerator.nextId(commonConfigData.nodeId());

        ReservedSlotsDTO reservedSlot = ReservedSlotsDTO.builder()
                .id(id)
                .userId(user.getUserId())
                .availableSlotId(slot.getId())
                .build();

        reservedSlotsRepository.save(reservedSlot);

        slot.setIsReserved(true);
        availableSlotsRepository.save(slot);
    }

    private AvailableSlotsDTO validateAndGetAvailableSlot(ReserveSlotCommand command) {
        AvailableSlotsDTO availableSlot = availableSlotsRepository.findById(command.getSlotId());
        if (availableSlot == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("Slot with id [%s] not found.", command.getSlotId()),
                    "Slot"
            )));
        }

        if (availableSlot.getIsReserved()) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.INCONSISTENT_DATA,
                    String.format("Slot with id [%s] already reserved.", command.getSlotId()),
                    "ReservedSlot"
            )));
        }
        return availableSlot;
    }

    private UserDTO validateAndGetUser(ReserveSlotCommand command) {
        UserCriteriaDTO userNameCriteria = new UserCriteriaDTO();
        userNameCriteria.setUsernameEquals(command.getUserName());

        UserDTO user = userRepository.getSingleResult(userNameCriteria);
        if (user == null) {
            throw new ReservationDomainException(List.of(new ErrorDTO(
                    ErrorCodeEnum.DATA_NOT_FOUND,
                    String.format("User with username [%s] not found.", command.getUserName()),
                    "User"
            )));
        }

        return user;
    }
}
