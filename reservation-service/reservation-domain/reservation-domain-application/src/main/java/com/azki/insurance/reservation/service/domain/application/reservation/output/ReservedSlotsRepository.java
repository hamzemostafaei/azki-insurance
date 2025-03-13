package com.azki.insurance.reservation.service.domain.application.reservation.output;

import com.azki.insurance.domain.application.output.repository.Repository;
import com.azki.insurance.reservation.service.domain.application.api.dto.ReservedSlotsDTO;
import com.azki.insurance.reservation.service.domain.application.api.dto.search.ReservedSlotsCriteriaDTO;

public interface ReservedSlotsRepository extends Repository<ReservedSlotsDTO, Long, ReservedSlotsCriteriaDTO> {
}
