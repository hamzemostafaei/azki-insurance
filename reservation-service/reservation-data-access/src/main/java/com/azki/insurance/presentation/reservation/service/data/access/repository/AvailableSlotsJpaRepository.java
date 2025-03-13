package com.azki.insurance.presentation.reservation.service.data.access.repository;

import com.azki.insurance.presentation.reservation.service.data.access.entity.AvailableSlotsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailableSlotsJpaRepository extends JpaRepository<AvailableSlotsEntity, Long> {

    @Query(" SELECT S FROM AvailableSlotsEntity S WHERE  S.isReserved = FALSE AND S.startTime >= :startTime ORDER BY S.startTime ASC")
    List<AvailableSlotsEntity> findNearestAvailableSlot(Date startTime, Pageable pageable);

    Page<AvailableSlotsEntity> findByIsReservedFalseOrderByStartTime(Pageable pageable);
}
