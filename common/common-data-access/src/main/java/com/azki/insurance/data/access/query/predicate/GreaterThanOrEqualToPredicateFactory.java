package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.application.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("GreaterThanOrEqualToPredicate")
public class GreaterThanOrEqualToPredicateFactory<E extends BaseJpaEntity,
                                                  T extends GenericConditionItem<?>>
        implements PredicateFactory<E, T> {

    public GreaterThanOrEqualToPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.GREATER_THAN_OR_EQUAL_TO,this);
    }
    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) {
       return cb.where(fieldName).ge(condition.getConditionData());
    }

}