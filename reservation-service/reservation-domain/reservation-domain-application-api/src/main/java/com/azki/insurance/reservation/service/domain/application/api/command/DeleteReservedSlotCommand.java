package com.azki.insurance.reservation.service.domain.application.api.command;

import com.azki.insurance.domain.application.api.command.Command;
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
