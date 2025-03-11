package com.azki.insurance.reservation.service.data.access.adapter;

import com.azki.insurance.data.access.adapter.impl.BaseJpaAdapterImpl;
import com.azki.insurance.reservation.service.data.access.entity.AvailableSlotsEntity;
import com.azki.insurance.reservation.service.data.access.repository.AvailableSlotsJpaRepository;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.query.AvailableSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AvailableSlotsJpaAdapterImpl extends BaseJpaAdapterImpl<AvailableSlotsDTO,
                                                                     Integer,
                                                                     AvailableSlotsEntity,
                                                                     AvailableSlotsCriteriaDTO,
                                                                     AvailableSlotsJpaRepository>
        implements AvailableSlotsRepository {
}
