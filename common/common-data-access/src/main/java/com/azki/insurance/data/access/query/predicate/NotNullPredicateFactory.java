package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.application.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("NotNullPredicate")
public class NotNullPredicateFactory<E extends BaseJpaEntity,
                                     T extends GenericConditionItem<?>>
        implements NullPredicateFactory<E, T> {

    public NotNullPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.NOT_NULL,this);
    }

    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName)throws IllegalArgumentException {
       return cb.where(fieldName).isNotNull();
    }
}