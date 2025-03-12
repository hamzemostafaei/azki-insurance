package com.azki.insurance.reservation.service.domain.ports.output;

import com.azki.insurance.domain.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.api.dto.ReservedSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.ReservedSlotsCriteriaDTO;

public interface ReservedSlotsRepository extends Repository<ReservedSlotsDTO, Long, ReservedSlotsCriteriaDTO> {
}
