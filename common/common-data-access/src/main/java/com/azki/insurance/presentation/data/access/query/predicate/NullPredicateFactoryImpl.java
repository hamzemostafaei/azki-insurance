package com.azki.insurance.presentation.data.access.query.predicate;

import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import com.azki.insurance.presentation.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.presentation.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("NullPredicate")
public class NullPredicateFactoryImpl<E extends BaseJpaEntity,
                                  T extends GenericConditionItem<?>>
        implements NullPredicateFactory<E, T> {

    public NullPredicateFactoryImpl() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.NULL,this);
    }

    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName) {
       return cb.where(fieldName).isNull();
    }
}