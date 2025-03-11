package com.azki.insurance.reservation.service.data.access.repository;

import com.azki.insurance.reservation.service.data.access.entity.AvailableSlotsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableSlotsJpaRepository extends JpaRepository<AvailableSlotsEntity, Integer> {
}
