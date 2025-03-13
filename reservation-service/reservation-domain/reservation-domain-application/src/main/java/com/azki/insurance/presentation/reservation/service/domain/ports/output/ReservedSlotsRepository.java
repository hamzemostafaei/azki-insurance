package com.azki.insurance.presentation.reservation.service.domain.ports.output;

import com.azki.insurance.presentation.domain.output.repository.Repository;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.ReservedSlotsDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.search.ReservedSlotsCriteriaDTO;

public interface ReservedSlotsRepository extends Repository<ReservedSlotsDTO, Long, ReservedSlotsCriteriaDTO> {
}
