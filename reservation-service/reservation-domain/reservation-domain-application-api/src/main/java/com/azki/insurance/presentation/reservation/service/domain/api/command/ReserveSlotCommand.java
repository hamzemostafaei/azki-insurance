package com.azki.insurance.presentation.reservation.service.domain.api.command;

import com.azki.insurance.presentation.domain.api.command.Command;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReserveSlotCommand extends Command {

    @NotEmpty
    private String userName;

    @NotEmpty
    private Long slotId;
}
