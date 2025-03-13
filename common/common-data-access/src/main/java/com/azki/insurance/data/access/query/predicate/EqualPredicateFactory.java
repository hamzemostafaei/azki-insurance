package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.BaseConditionItem;
import com.azki.insurance.domain.application.api.dto.query.condition.ConditionTypeEnum;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("EqualPredicate")
public class EqualPredicateFactory<E extends BaseJpaEntity,
                                   T extends BaseConditionItem<?>>
        implements PredicateFactory<E, T> {

    public EqualPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.EQUAL,this);
    }
    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) {
       return cb.where(fieldName).eq(condition.getConditionData());
    }

}