package com.azki.insurance.reservation.service.data.access.repository;

import com.azki.insurance.reservation.service.data.access.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsernameOrEmail(String username, String email);
}
