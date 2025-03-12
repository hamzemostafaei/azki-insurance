package com.azki.insurance.reservation.service.domain.api.command;

import com.azki.insurance.domain.api.command.Command;
import jakarta.annotation.Nonnull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReserveSlotCommand extends Command {

    @Nonnull
    private String userName;
    private Integer slotId;
}
