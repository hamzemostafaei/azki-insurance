package com.azki.insurance.presentation.reservation.service.data.access.mapper;

import com.azki.insurance.presentation.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.presentation.reservation.service.data.access.entity.AvailableSlotsEntity;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.AvailableSlotsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationSlotsJpaEntityMapper extends GenericJpaEntityMapper<AvailableSlotsEntity, AvailableSlotsDTO> {
}
