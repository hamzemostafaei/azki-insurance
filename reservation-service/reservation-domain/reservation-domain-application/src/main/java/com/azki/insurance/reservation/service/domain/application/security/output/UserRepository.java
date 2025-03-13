package com.azki.insurance.reservation.service.domain.application.security.output;

import com.azki.insurance.domain.application.output.repository.Repository;
import com.azki.insurance.domain.application.api.dto.UserDTO;
import com.azki.insurance.reservation.service.domain.application.api.dto.search.UserCriteriaDTO;

public interface UserRepository extends Repository<UserDTO, Integer, UserCriteriaDTO> {

    Boolean existsByUsernameOrEmail(String username, String email);
    Long getNextUserId();
}
