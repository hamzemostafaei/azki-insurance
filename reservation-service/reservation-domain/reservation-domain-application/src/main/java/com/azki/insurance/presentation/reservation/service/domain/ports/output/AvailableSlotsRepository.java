package com.azki.insurance.presentation.reservation.service.domain.ports.output;

import com.azki.insurance.presentation.domain.output.repository.Repository;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface AvailableSlotsRepository extends Repository<AvailableSlotsDTO, Long, AvailableSlotsCriteriaDTO> {
    AvailableSlotsDTO findNearestAvailableSlot(Date startTime);
    Page<AvailableSlotsDTO> getAvailableSlots(Pageable pageable);
}
