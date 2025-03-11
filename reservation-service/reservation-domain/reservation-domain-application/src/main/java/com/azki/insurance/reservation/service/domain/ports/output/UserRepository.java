package com.azki.insurance.reservation.service.domain.ports.output;

import com.azki.insurance.domain.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.api.dto.UserDTO;
import com.azki.insurance.reservation.service.domain.api.query.UserCriteriaDTO;

public interface UserRepository extends Repository<UserDTO, Integer, UserCriteriaDTO> {

    Boolean existsByUsernameOrEmail(String username, String email);
}
