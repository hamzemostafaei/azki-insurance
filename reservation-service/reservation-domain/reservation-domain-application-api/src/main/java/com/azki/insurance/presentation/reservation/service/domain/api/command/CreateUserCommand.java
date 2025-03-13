package com.azki.insurance.presentation.reservation.service.domain.api.command;

import com.azki.insurance.presentation.domain.api.command.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateUserCommand extends Command {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    @Email
    private String email;
}
