package com.azki.insurance.reservation.service.data.access.mapper;

import com.azki.insurance.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.reservation.service.data.access.entity.ReservedSlotsEntity;
import com.azki.insurance.reservation.service.domain.application.api.dto.ReservedSlotsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservedSlotsJpaEntityMapper extends GenericJpaEntityMapper<ReservedSlotsEntity, ReservedSlotsDTO> {
}
