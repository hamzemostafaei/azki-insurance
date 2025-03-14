package com.azki.insurance.reservation.service.domain.application.api.command;

import com.azki.insurance.domain.application.api.command.Command;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthenticationCommand extends Command {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;
}
