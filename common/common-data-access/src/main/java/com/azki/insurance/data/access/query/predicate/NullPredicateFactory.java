package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;

public interface NullPredicateFactory<E extends BaseJpaEntity,
                                      T extends GenericConditionItem<?>>
        extends PredicateFactory<E, T> {

    CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName);

    @Override
    default CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Null predicate factory does not have condition, so use the method which has no condition.");
    }
}