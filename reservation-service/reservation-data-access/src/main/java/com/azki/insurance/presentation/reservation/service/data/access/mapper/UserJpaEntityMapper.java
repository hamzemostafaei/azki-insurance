package com.azki.insurance.presentation.reservation.service.data.access.mapper;

import com.azki.insurance.presentation.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.presentation.reservation.service.data.access.entity.UserEntity;
import com.azki.insurance.presentation.domain.api.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserJpaEntityMapper extends GenericJpaEntityMapper<UserEntity, UserDTO> {
}
