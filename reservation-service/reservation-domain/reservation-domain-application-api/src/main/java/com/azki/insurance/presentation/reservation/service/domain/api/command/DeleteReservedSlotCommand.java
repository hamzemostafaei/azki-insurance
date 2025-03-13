package com.azki.insurance.presentation.reservation.service.domain.api.command;

import com.azki.insurance.presentation.domain.api.command.Command;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeleteReservedSlotCommand extends Command {

    @NotEmpty
    private String username;

    @NotNull
    private Long reservedSlotId;
}
