package com.azki.insurance.reservation.service.domain.ports.input.reservation;

import com.azki.insurance.domain.api.command.CommandResult;
import com.azki.insurance.domain.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.api.command.CreateReservationCommand;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class CreateReservationCommandHandlerImpl extends BaseCommandHandler<CreateReservationCommand, CommandResult<AvailableSlotsDTO>> {

    private final AvailableSlotsRepository availableSlotsRepository;

    @Override
    protected CommandResult<AvailableSlotsDTO> execute(CreateReservationCommand command) {
        AvailableSlotsDTO reservation = new AvailableSlotsDTO();
        reservation.setStartTime(command.getStartTime());
        reservation.setEndTime(command.getEndTime());

        AvailableSlotsDTO resultData = availableSlotsRepository.save(reservation);

        CommandResult<AvailableSlotsDTO> result = new CommandResult<>();
        result.setData(resultData);

        return result;
    }
}
