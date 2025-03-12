package com.azki.insurance.domain.output.repository;


import com.azki.insurance.domain.api.dto.BaseVersionedDTO;
import com.azki.insurance.domain.api.dto.query.SearchCriteria;
import com.azki.insurance.domain.core.exception.DomainException;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface Repository<D extends BaseVersionedDTO, I extends Serializable, C extends SearchCriteria> {

    D save(D dto);

    D findById(I id);

    List<D> listSearch(C criteria);

    boolean exists(C criteria);

    D getSingleResult(C criteria);

    public Page<D> search(C criteria) throws DomainException;

}
