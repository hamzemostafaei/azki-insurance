package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("LessThanOrEqualToPredicate")
public class LessThanOrEqualToPredicateFactory<E extends BaseJpaEntity,
                                               T extends GenericConditionItem<?>>
        implements PredicateFactory<E, T> {

    public LessThanOrEqualToPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.LESS_THAN_OR_EQUAL_TO,this);
    }
    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T condition) {
       return cb.where(fieldName).le(condition.getConditionData());
    }

}