package com.azki.insurance.reservation.service.domain.ports.output;

import com.azki.insurance.domain.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.query.AvailableSlotsCriteriaDTO;

public interface AvailableSlotsRepository extends Repository<AvailableSlotsDTO, Integer, AvailableSlotsCriteriaDTO> {
}
