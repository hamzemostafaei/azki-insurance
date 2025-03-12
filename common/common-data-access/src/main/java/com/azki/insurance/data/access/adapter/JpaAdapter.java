package com.azki.insurance.data.access.adapter;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.api.dto.BaseVersionedDTO;
import com.azki.insurance.domain.api.dto.query.SearchCriteria;
import com.azki.insurance.domain.core.exception.DomainException;
import com.azki.insurance.domain.output.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface JpaAdapter<D extends BaseVersionedDTO,
                            I extends Serializable,
                            E extends BaseJpaEntity,
                            C extends SearchCriteria,
                            R extends JpaRepository<E, I>>
        extends Repository<D, I,C> {

    public List<String> getFieldNames();

    D save(D dto);
    Iterable<D> saveAll(Iterable<D> dtoList);

    D findById(I id);

    List<D> findAllById(Collection<I> idList);

    Page<D> search(C criteria) throws DomainException;

    List<D> listSearch(C criteria);

    boolean existsById(I id);

    boolean exists(C criteria);

    D getSingleResult(C criteria);
}
