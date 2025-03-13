package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.ReserveSlotsCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.ports.input.reservation.caching.ReservationCacheEvictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReserveSlotsCommandHandlerImpl extends BaseCommandHandler<ReserveSlotsCommand, CommandResult<AvailableSlotsDTO>> {

    private final SlotReservationHelper slotReservationHelper;
    private final ReservationCacheEvictionService cacheEvictionService;

    @Override
    protected CommandResult<AvailableSlotsDTO> execute(ReserveSlotsCommand command) {

        UserDTO user = slotReservationHelper.validateAndGetUser(command.getUserName());
        List<AvailableSlotsDTO> availableSlots = slotReservationHelper.validateAndGetAvailableSlots(command.getSlotIds());

        slotReservationHelper.reserveSlots(user, availableSlots);

        cacheEvictionService.evictAvailableSlotsCache();

        return CommandResult.success();

    }
}
