package com.azki.insurance.reservation.service.data.access.repository;

import com.azki.insurance.reservation.service.data.access.entity.ReservedSlotsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedSlotsJpsRepository extends JpaRepository<ReservedSlotsEntity, Long> {
}
