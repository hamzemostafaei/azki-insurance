package com.azki.insurance.data.access.query.predicate;


import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.domain.application.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.application.api.dto.query.condition.GenericConditionItem;

import java.util.HashMap;
import java.util.Map;

public class PredicateFactoryRegistry {
    private static final Map<ConditionTypeEnum, PredicateFactory<? extends BaseJpaEntity, ? extends GenericConditionItem<?>>> builderMap = new HashMap<>();

    public static synchronized void register(ConditionTypeEnum conditionType,
                                             PredicateFactory<? extends BaseJpaEntity, ? extends GenericConditionItem<?>> conditionItem) {

        builderMap.putIfAbsent(conditionType, conditionItem);
    }

    @SuppressWarnings("unchecked")
    public static <E extends BaseJpaEntity, T extends GenericConditionItem<?>> PredicateFactory<E, T> getBuilder(ConditionTypeEnum conditionType) {
        return (PredicateFactory<E, T>) builderMap.get(conditionType);
    }
}