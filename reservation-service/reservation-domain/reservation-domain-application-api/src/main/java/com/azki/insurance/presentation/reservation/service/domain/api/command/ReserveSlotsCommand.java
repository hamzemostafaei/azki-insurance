package com.azki.insurance.presentation.reservation.service.domain.api.command;

import com.azki.insurance.presentation.domain.api.command.Command;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReserveSlotsCommand extends Command {

    @NotEmpty
    private String userName;

    @NotEmpty(message = "At least one slot ID is required.")
    @Size(min = 1, max = 10, message = "The number of slot IDs must be between 1 and 10.")
    private List<Long> slotIds;
}
