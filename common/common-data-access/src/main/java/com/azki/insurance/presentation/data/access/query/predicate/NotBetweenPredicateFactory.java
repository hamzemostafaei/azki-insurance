package com.azki.insurance.presentation.data.access.query.predicate;

import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import com.azki.insurance.presentation.data.access.query.BetweenCriteriaDTO;
import com.azki.insurance.presentation.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.presentation.domain.api.dto.query.condition.GenericConditionItem;
import com.blazebit.persistence.CriteriaBuilder;
import org.springframework.stereotype.Component;

@Component("NotBetweenPredicate")
public class NotBetweenPredicateFactory<E extends BaseJpaEntity,
                                        T extends GenericConditionItem<BetweenCriteriaDTO<?,?>>>
        implements PredicateFactory<E, T> {

    public NotBetweenPredicateFactory() {
        PredicateFactoryRegistry.register(ConditionTypeEnum.NOT_BETWEEN,this);
    }

    @Override
    public CriteriaBuilder<E> buildPredicate(CriteriaBuilder<E> cb, String fieldName, T between) {
        cb.where(fieldName).notBetween(between.getConditionData().getFrom()).and(between.getConditionData().getTo());
        return cb;
    }

}