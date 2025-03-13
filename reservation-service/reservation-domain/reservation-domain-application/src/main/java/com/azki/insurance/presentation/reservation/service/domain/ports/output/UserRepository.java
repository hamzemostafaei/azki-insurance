package com.azki.insurance.presentation.reservation.service.domain.ports.output;

import com.azki.insurance.presentation.domain.output.repository.Repository;
import com.azki.insurance.presentation.domain.api.dto.UserDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.UserCriteriaDTO;

public interface UserRepository extends Repository<UserDTO, Integer, UserCriteriaDTO> {

    Boolean existsByUsernameOrEmail(String username, String email);
    Long getNextUserId();
}
