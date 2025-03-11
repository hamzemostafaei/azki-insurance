package com.azki.insurance.data.access.adapter;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.api.dto.BaseVersionedDTO;
import com.azki.insurance.domain.api.dto.query.SearchCriteria;
import com.azki.insurance.domain.output.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface JpaAdapter<D extends BaseVersionedDTO,
                            I extends Serializable,
                            E extends BaseJpaEntity,
                            C extends SearchCriteria,
                            R extends JpaRepository<E, I>>
        extends Repository<D, I,C> {

    D save(D dto);

    D findById(I id);

    List<D> listSearch(C criteria);

    public boolean existsById(I id);

    boolean exists(C criteria);

    D getSingleResult(C criteria);
}
