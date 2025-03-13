package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.ReserveSlotCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.ports.input.reservation.caching.ReservationCacheEvictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ReserveSlotCommandHandlerImpl extends BaseCommandHandler<ReserveSlotCommand, CommandResult<Void>> {

    private final SlotReservationHelper slotReservationHelper;
    private final ReservationCacheEvictionService cacheEvictionService;

    @Override
    protected CommandResult<Void> execute(ReserveSlotCommand command) {

        UserDTO user = slotReservationHelper.validateAndGetUser(command.getUserName());
        AvailableSlotsDTO slot = slotReservationHelper.validateAndGetAvailableSlot(command);
        slotReservationHelper.reserveSlot(user, slot);

        cacheEvictionService.evictAvailableSlotsCache();

        return CommandResult.success();
    }

}
