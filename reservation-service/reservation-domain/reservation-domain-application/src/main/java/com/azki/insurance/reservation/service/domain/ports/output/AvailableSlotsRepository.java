package com.azki.insurance.reservation.service.domain.ports.output;

import com.azki.insurance.domain.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;

import java.util.Date;

public interface AvailableSlotsRepository extends Repository<AvailableSlotsDTO, Integer, AvailableSlotsCriteriaDTO> {
    AvailableSlotsDTO findNearestAvailableSlot(Date startTime);
}
