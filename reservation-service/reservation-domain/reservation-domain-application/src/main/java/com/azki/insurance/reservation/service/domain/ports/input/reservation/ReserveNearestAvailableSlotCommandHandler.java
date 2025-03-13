package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.ReserveNearestAvailableSlotCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.ports.input.reservation.caching.ReservationCacheEvictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ReserveNearestAvailableSlotCommandHandler extends BaseCommandHandler<ReserveNearestAvailableSlotCommand, CommandResult<AvailableSlotsDTO>> {

    private final SlotReservationHelper slotReservationHelper;
    private final ReservationCacheEvictionService cacheEvictionService;

    @Override
    protected CommandResult<AvailableSlotsDTO> execute(ReserveNearestAvailableSlotCommand command) {

        UserDTO user = slotReservationHelper.validateAndGetUser(command.getUserName());

        Date startTime = command.getStartTime();
        if (startTime == null) {
            startTime = new Date();
        }
        AvailableSlotsDTO nearestAvailableSlot = slotReservationHelper.getNearestAvailableSlot(startTime);
        AvailableSlotsDTO reservedSlot = slotReservationHelper.reserveSlot(user, nearestAvailableSlot);

        cacheEvictionService.evictAvailableSlotsCache();

        return CommandResult.success(reservedSlot);
    }
}
