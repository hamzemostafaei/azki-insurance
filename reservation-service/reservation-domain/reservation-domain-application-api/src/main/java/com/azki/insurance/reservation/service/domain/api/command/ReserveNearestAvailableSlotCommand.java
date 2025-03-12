package com.azki.insurance.reservation.service.domain.api.command;

import com.azki.insurance.domain.api.command.Command;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReserveNearestAvailableSlotCommand extends Command {

    @NotEmpty
    private String userName;

    @FutureOrPresent(message = "Start time must be in the present or future.")
    private Date startTime;
}
