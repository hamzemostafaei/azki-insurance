package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.application.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("NotEqualPredicate")
public class NotEqualPredicateFactory<E extends BaseJpaEntity,
                                      T extends GenericConditionItem<?>>
        implements PredicateFactory<E, T> {

    public NotEqualPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.NOT_EQUAL,this);
    }
    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) {
       return cb.where(fieldName).notEq(condition.getConditionData());
    }

}