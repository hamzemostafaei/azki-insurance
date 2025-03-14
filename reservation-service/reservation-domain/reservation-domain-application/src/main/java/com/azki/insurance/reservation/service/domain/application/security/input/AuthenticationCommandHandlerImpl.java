package com.azki.insurance.reservation.service.domain.application.security.input;

import com.azki.insurance.common.app.config.CommonConfigData;
import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.SecurityHelper;
import com.azki.insurance.domain.application.api.command.CommandResult;
import com.azki.insurance.domain.application.api.dto.UserDTO;
import com.azki.insurance.domain.application.input.BaseCommandHandler;
import com.azki.insurance.reservation.service.domain.application.api.command.AuthenticationCommand;
import com.azki.insurance.reservation.service.domain.application.api.dto.search.UserCriteriaDTO;
import com.azki.insurance.reservation.service.domain.application.api.exception.ReservationDomainException;
import com.azki.insurance.reservation.service.domain.application.security.output.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class AuthenticationCommandHandlerImpl extends BaseCommandHandler<AuthenticationCommand, CommandResult<UserDTO>> {

    private final UserRepository userRepository;
    private final CommonConfigData commonConfigData;

    @Override
    protected CommandResult<UserDTO> execute(AuthenticationCommand command) {
        UserDTO user = findUserByUsername(command.getUserName());

        if (!validateUserCredentials(user, command.getPassword())) {
            if (log.isWarnEnabled()) {
                log.warn("Invalid password attempt for user: {}", user.getUsername());
            }
            throw new ReservationDomainException(List.of(createSecurityError()));
        }

        Collection<? extends GrantedAuthority> authorities = determineUserAuthorities(user);

        authenticateUser(user, authorities);

        return CommandResult.success(user);
    }

    private UserDTO findUserByUsername(String username) {
        UserCriteriaDTO criteria = new UserCriteriaDTO();
        criteria.setUsernameEquals(username);
        UserDTO user = userRepository.getSingleResult(criteria);

        if (user == null) {
            throw new ReservationDomainException(List.of(createSecurityError()));
        }
        return user;
    }

    private boolean validateUserCredentials(UserDTO user, String rawPassword) {
        return SecurityHelper.validateUserPassword(user.getUsername(), rawPassword, user.getPassword());
    }

    private ErrorDTO createSecurityError() {
        return new ErrorDTO(ErrorCodeEnum.SECURITY_ERROR, "User name or password is incorrect.", "Security");
    }

    private Collection<? extends GrantedAuthority> determineUserAuthorities(UserDTO user) {
        return Objects.equals(user.getUserId(), commonConfigData.adminUserId())
                ? List.of(new SimpleGrantedAuthority(commonConfigData.adminRoleName()))
                : List.of(new SimpleGrantedAuthority(commonConfigData.userRoleName()));
    }

    private void authenticateUser(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authenticatedUser =
                UsernamePasswordAuthenticationToken.authenticated(user, user.getUsername(), authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
