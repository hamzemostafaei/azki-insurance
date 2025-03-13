package com.azki.insurance.presentation.reservation.service.data.access.mapper;

import com.azki.insurance.presentation.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.presentation.reservation.service.data.access.entity.ReservedSlotsEntity;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.ReservedSlotsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservedSlotsJpaEntityMapper extends GenericJpaEntityMapper<ReservedSlotsEntity, ReservedSlotsDTO> {
}
