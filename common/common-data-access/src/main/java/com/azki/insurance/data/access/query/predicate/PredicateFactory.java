package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;

public interface PredicateFactory<E extends BaseJpaEntity, T extends GenericConditionItem<?>> {

    CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition);

}