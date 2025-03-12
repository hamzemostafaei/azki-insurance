package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.api.dto.UserDTO;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.DeleteReservedSlotCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.ReservedSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.ReservedSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import com.azki.insurance.reservation.service.domain.ports.output.ReservedSlotsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class DeleteReservedSlotCommandHandler extends BaseCommandHandler<DeleteReservedSlotCommand, CommandResult<Void>> {

    private final ReservedSlotsRepository reservedSlotsRepository;
    private final SlotReservationHelper slotReservationHelper;
    private final AvailableSlotsRepository availableSlotsRepository;

    @Override
    protected CommandResult<Void> execute(DeleteReservedSlotCommand command) {

        UserDTO user = slotReservationHelper.validateAndGetUser(command.getUsername());
        AvailableSlotsDTO slot = slotReservationHelper.validateAndGetReservedSlot(command.getReservedSlotId());

        ReservedSlotsCriteriaDTO searchCriteria = new ReservedSlotsCriteriaDTO();
        searchCriteria.setUserIdEquals(user.getUserId());
        searchCriteria.setSlotIdEquals(command.getReservedSlotId());

        ReservedSlotsDTO reservedSlot = reservedSlotsRepository.getSingleResult(searchCriteria);

        reservedSlotsRepository.deleteById(reservedSlot.getId());
        slot.setIsReserved(false);

        availableSlotsRepository.save(slot);

        return CommandResult.success();
    }
}
