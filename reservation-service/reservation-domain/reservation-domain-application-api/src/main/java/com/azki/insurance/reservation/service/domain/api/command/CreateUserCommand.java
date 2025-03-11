package com.azki.insurance.reservation.service.domain.api.command;

import com.azki.insurance.domain.api.command.Command;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateUserCommand extends Command {

    private String userName;
    private String password;
    private String email;
}
