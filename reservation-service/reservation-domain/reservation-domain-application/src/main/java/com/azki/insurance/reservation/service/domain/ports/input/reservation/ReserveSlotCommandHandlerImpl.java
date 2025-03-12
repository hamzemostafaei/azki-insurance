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


    private final SlotReservationHelper slotReservationHelper;

    @Override
    protected CommandResult<Void> execute(ReserveSlotCommand command) {

        UserDTO user = slotReservationHelper.validateAndGetUser(command.getUserName());
        AvailableSlotsDTO slot = slotReservationHelper.validateAndGetAvailableSlot(command);
        slotReservationHelper.reserveSlot(user, slot);

        return CommandResult.success();
    }

}
