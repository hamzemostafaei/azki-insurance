package com.azki.insurance.reservation.service.domain.application.api.command;

import com.azki.insurance.domain.application.api.command.Command;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginCommand extends Command {
    private String userName;
    private String password;
}
