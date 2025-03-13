package com.azki.insurance.presentation.reservation.service.data.access.adapter;

import com.azki.insurance.presentation.data.access.adapter.impl.BaseJpaAdapterImpl;
import com.azki.insurance.presentation.reservation.service.data.access.entity.UserEntity;
import com.azki.insurance.presentation.reservation.service.data.access.repository.UserJpaRepository;
import com.azki.insurance.presentation.domain.api.dto.UserDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.UserCriteriaDTO;
import com.azki.insurance.presentation.reservation.service.domain.ports.output.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersJpaAdapterImpl extends BaseJpaAdapterImpl<UserDTO,
                                                            Integer,
                                                            UserEntity,
                                                            UserCriteriaDTO,
                                                            UserJpaRepository>
        implements UserRepository {

    @Override
    public Boolean existsByUsernameOrEmail(String username, String email) {
        return repository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public Long getNextUserId() {
        return repository.getNextUserId();
    }
}
