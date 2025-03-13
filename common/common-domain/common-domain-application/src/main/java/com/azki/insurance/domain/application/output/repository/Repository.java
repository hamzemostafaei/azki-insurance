package com.azki.insurance.domain.application.output.repository;


import com.azki.insurance.domain.application.api.dto.BaseVersionedDTO;
import com.azki.insurance.domain.application.api.dto.query.SearchCriteria;
import com.azki.insurance.domain.core.exception.DomainException;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Repository<D extends BaseVersionedDTO, I extends Serializable, C extends SearchCriteria> {

    D save(D dto);

    Iterable<D> saveAll(Iterable<D> dtoList);

    D findById(I id);

    List<D> findAllById(Collection<I> idList);

    List<D> listSearch(C criteria);

    boolean exists(C criteria);

    D getSingleResult(C criteria);

    Page<D> search(C criteria) throws DomainException;

    public void deleteById(I id);

}
