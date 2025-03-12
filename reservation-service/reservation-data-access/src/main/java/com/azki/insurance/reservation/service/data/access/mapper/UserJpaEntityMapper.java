package com.azki.insurance.reservation.service.data.access.mapper;

import com.azki.insurance.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.reservation.service.data.access.entity.UserEntity;
import com.azki.insurance.domain.api.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserJpaEntityMapper extends GenericJpaEntityMapper<UserEntity, UserDTO> {
}
