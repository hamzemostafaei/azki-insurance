package com.azki.insurance.reservation.service.domain.api.command;

import com.azki.insurance.domain.api.command.Command;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReserveSlotsCommand extends Command {
    @Nonnull
    private String userName;
    private List<Integer> slotIds;
}
