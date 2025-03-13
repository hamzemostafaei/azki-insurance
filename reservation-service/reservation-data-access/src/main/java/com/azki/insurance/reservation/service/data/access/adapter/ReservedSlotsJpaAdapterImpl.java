package com.azki.insurance.reservation.service.data.access.adapter;

import com.azki.insurance.data.access.adapter.impl.BaseJpaAdapterImpl;
import com.azki.insurance.reservation.service.data.access.entity.ReservedSlotsEntity;
import com.azki.insurance.reservation.service.data.access.repository.ReservedSlotsJpsRepository;
import com.azki.insurance.reservation.service.domain.application.api.dto.ReservedSlotsDTO;
import com.azki.insurance.reservation.service.domain.application.api.dto.search.ReservedSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.application.reservation.output.ReservedSlotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReservedSlotsJpaAdapterImpl extends BaseJpaAdapterImpl<ReservedSlotsDTO,
                                                                    Long,
                                                                    ReservedSlotsEntity,
                                                                    ReservedSlotsCriteriaDTO,
                                                                    ReservedSlotsJpsRepository>
        implements ReservedSlotsRepository {


}
