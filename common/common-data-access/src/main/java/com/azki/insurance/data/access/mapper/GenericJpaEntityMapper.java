package com.azki.insurance.data.access.mapper;


import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.BaseVersionedDTO;

public interface GenericJpaEntityMapper<E extends BaseJpaEntity, D extends BaseVersionedDTO> {
    D entityToDTO(E entity);

    E dtoToEntity(D dto);
}
