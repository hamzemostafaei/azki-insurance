package com.azki.insurance.reservation.service.domain.application.reservation.output;

import com.azki.insurance.domain.application.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.application.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.application.api.dto.search.AvailableSlotsCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface AvailableSlotsRepository extends Repository<AvailableSlotsDTO, Long, AvailableSlotsCriteriaDTO> {
    AvailableSlotsDTO findNearestAvailableSlot(Date startTime);
    Page<AvailableSlotsDTO> getAvailableSlots(Pageable pageable);
}
