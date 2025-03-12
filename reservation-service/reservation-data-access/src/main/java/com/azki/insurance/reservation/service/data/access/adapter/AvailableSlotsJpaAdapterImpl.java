package com.azki.insurance.reservation.service.data.access.adapter;

import com.azki.insurance.data.access.adapter.impl.BaseJpaAdapterImpl;
import com.azki.insurance.reservation.service.data.access.entity.AvailableSlotsEntity;
import com.azki.insurance.reservation.service.data.access.repository.AvailableSlotsJpaRepository;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AvailableSlotsJpaAdapterImpl extends BaseJpaAdapterImpl<AvailableSlotsDTO,
                                                                     Long,
                                                                     AvailableSlotsEntity,
                                                                     AvailableSlotsCriteriaDTO,
                                                                     AvailableSlotsJpaRepository>
        implements AvailableSlotsRepository {

    @Override
    public AvailableSlotsDTO findNearestAvailableSlot(Date startTime) {
        Pageable pageable = PageRequest.of(0, 1);
        List<AvailableSlotsEntity> nearestAvailableSlot = repository.findNearestAvailableSlot(startTime, pageable);
        return entityToDTO(nearestAvailableSlot.isEmpty() ? null : nearestAvailableSlot.getFirst());
    }

    @Override
    public Page<AvailableSlotsDTO> getAvailableSlots(Pageable pageable) {
        return repository.findByIsReservedFalseOrderByStartTime(pageable).map(this::entityToDTO);
    }
}
