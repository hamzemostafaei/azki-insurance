package com.azki.insurance.presentation.data.access.mapper;


import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import com.azki.insurance.presentation.domain.api.dto.BaseVersionedDTO;

public interface GenericJpaEntityMapper<E extends BaseJpaEntity, D extends BaseVersionedDTO> {
    D entityToDTO(E entity);

    E dtoToEntity(D dto);
}
