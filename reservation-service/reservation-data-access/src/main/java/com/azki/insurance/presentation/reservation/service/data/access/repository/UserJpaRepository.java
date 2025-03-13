package com.azki.insurance.presentation.reservation.service.data.access.repository;

import com.azki.insurance.presentation.reservation.service.data.access.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsernameOrEmail(String username, String email);

    @Query("SELECT COALESCE(MAX(U.userId), 1) + 1 FROM UserEntity U")
    Long getNextUserId();
}
