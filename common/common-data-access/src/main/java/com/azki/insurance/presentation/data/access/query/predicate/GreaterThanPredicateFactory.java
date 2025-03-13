package com.azki.insurance.presentation.data.access.query.predicate;

import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import com.azki.insurance.presentation.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.presentation.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("GreaterThanPredicate")
public class GreaterThanPredicateFactory<E extends BaseJpaEntity,
                                         T extends GenericConditionItem<?>>
        implements PredicateFactory<E, T> {

    public GreaterThanPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.GREATER_THAN,this);
    }
    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) {
       return cb.where(fieldName).gt(condition.getConditionData());
    }

}