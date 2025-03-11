package com.azki.insurance.data.access.query.predicate;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.data.access.query.BetweenCriteriaDTO;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("BetweenPredicate")
public class BetweenPredicateFactory<E extends BaseJpaEntity,
                                     T extends GenericConditionItem<BetweenCriteriaDTO<?, ?>>>
                                     implements PredicateFactory<E, T> {

    public BetweenPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.BETWEEN, this);
    }

    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T between) {
        cb.where(fieldName).between(between.getConditionData().getFrom()).and(between.getConditionData().getTo());
        return cb;
    }

}