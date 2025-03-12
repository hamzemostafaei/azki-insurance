package com.azki.insurance.data.access.adapter.impl;

import com.azki.insurance.common.core.data.ErrorCodeEnum;
import com.azki.insurance.common.core.data.ErrorDTO;
import com.azki.insurance.common.utility.ReflectionUtil;
import com.azki.insurance.data.access.adapter.JpaAdapter;
import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.data.access.mapper.GenericJpaEntityMapper;
import com.azki.insurance.data.access.query.predicate.PredicateFactory;
import com.azki.insurance.data.access.query.predicate.PredicateFactoryRegistry;
import com.azki.insurance.domain.api.dto.BaseVersionedDTO;
import com.azki.insurance.domain.api.dto.SortDTO;
import com.azki.insurance.domain.api.dto.SortDirectionEnum;
import com.azki.insurance.domain.api.dto.query.SearchCriteria;
import com.azki.insurance.domain.api.dto.query.condition.Condition;
import com.azki.insurance.domain.api.dto.query.condition.ConditionTypeEnum;
import com.azki.insurance.domain.api.dto.query.condition.GenericConditionItem;
import com.azki.insurance.domain.core.exception.DomainException;
import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class BaseJpaAdapterImpl<D  extends BaseVersionedDTO,
                                         I  extends Serializable,
                                         E  extends BaseJpaEntity,
                                         C extends SearchCriteria,
                                         R   extends JpaRepository<E,I>>
        implements JpaAdapter<D,I,E,C,R> {

    private final Class<I> idClass;
    private final Class<E> entityClass;
    private final Class<C> criteriaClass;

    @Autowired
    protected R repository;

    @Autowired
    private GenericJpaEntityMapper<E, D> mapper;

    @Autowired
    protected CriteriaBuilderFactory criteriaBuilderFactory;

    @Autowired
    protected EntityManager entityManager;

    private final List<String> fieldNames;

    public BaseJpaAdapterImpl() {
        this.idClass = ReflectionUtil.getGenericParameterType(this, 1);
        this.entityClass = ReflectionUtil.getGenericParameterType(this, 2);
        this.criteriaClass = ReflectionUtil.getGenericParameterType(this, 3);
        this.fieldNames = ReflectionUtil.getFieldNames(this.entityClass);
    }

    @Override
    public D save(D dto) {
        E entity = dtoToEntity(dto);
        E savedEntity = repository.save(entity);
        return entityToDTO(savedEntity);
    }

    @Override
    public D findById(I id) {
        Optional<E> result = repository.findById(id);
        return result
                .map(this::entityToDTO)
                .orElse(null);
    }

    @Override
    public Page<D> search(C criteria) throws DomainException {

        CriteriaBuilder<E> criteriaBuilder = criteriaBuilderFactory.create(entityManager, entityClass);

        buildPredicate(criteria, criteriaBuilder);

        List<Sort.Order> sortItems = buildSortItems(criteria, criteriaBuilder);

        List<E> resultList;
        PageImpl<D> result = null;

        if (ObjectUtils.isNotEmpty(criteria.getOffset()) && ObjectUtils.isNotEmpty(criteria.getPageSize())) {
            int offset = criteria.getOffset();
            int pageSize = criteria.getPageSize();

            long totalResults;
            if (BooleanUtils.isTrue(criteria.getReturnTotalSize())) {
                totalResults = criteriaBuilder.getCountQuery().getSingleResult();
            } else {
                totalResults = -1;
            }

            criteriaBuilder.setFirstResult(offset * pageSize);
            criteriaBuilder.setMaxResults(pageSize);

            resultList = criteriaBuilder.getResultList();
            List<D> ds = entityCollectionToDTO(resultList);

            Pageable pageable = PageRequest.of(offset, pageSize, Sort.by(sortItems));

            result = new PageImpl<>(ds, pageable, totalResults);
        } else {
            resultList = criteriaBuilder.getResultList();
            if (!CollectionUtils.isEmpty(resultList)) {
                List<D> ds = entityCollectionToDTO(resultList);
                Pageable pageable = PageRequest.of(0, ds.size(), Sort.by(sortItems));
                result = new PageImpl<>(ds, pageable, ds.size());
            }
        }

        return result;
    }

    @Override
    public List<D> listSearch(C criteria) {
        CriteriaBuilder<E> criteriaBuilder = criteriaBuilderFactory.create(entityManager, entityClass);

        buildPredicate(criteria, criteriaBuilder);

        List<E> resultList = criteriaBuilder.getResultList();
        List<D> ds = null;
        if (!CollectionUtils.isEmpty(resultList)) {
            ds = entityCollectionToDTO(resultList);
        }

        return ds;
    }

    protected List<D> entityCollectionToDTO(Iterable<E> entities) {
        if (entities == null) {
            return null;
        }

        List<D> dtoList = new ArrayList<>();
        for (E entity : entities) {
            dtoList.add(entityToDTO(entity));
        }

        return dtoList;
    }

    protected D entityToDTO(E entity) {
        if (entity == null) {
            return null;
        }

        return mapper.entityToDTO(entity);
    }

    protected E dtoToEntity(D dto) {
        if (dto == null) {
            return null;
        }

        E entity = mapper.dtoToEntity(dto);

        entity = this.entityManager.merge(entity);

        return entity;
    }

    @Override
    public boolean exists(C criteria) {
        CriteriaBuilder<E> criteriaBuilder = criteriaBuilderFactory.create(entityManager, entityClass);
        buildPredicate(criteria, criteriaBuilder);
        Long count = criteriaBuilder.getCountQuery().getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsById(I id) {
        return repository.existsById(id);
    }

    @Override
    public List<String> getFieldNames() {
        return fieldNames;
    }

    @Override
    public D getSingleResult(C criteria) {
        CriteriaBuilder<E> criteriaBuilder = criteriaBuilderFactory.create(entityManager, entityClass);

        buildPredicate(criteria, criteriaBuilder);

        E singleResult;
        try {
            singleResult = criteriaBuilder.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return entityToDTO(singleResult);
    }

    private void buildPredicate(C criteria, CriteriaBuilder<E> criteriaBuilder) {

        List<String> fieldNames = ReflectionUtil.getFieldNames(criteriaClass);

        for (String fieldName : fieldNames) {
            Condition fieldAnnotation = ReflectionUtil.getFieldAnnotation(criteriaClass, fieldName, Condition.class);
            if (ObjectUtils.isNotEmpty(fieldAnnotation)) {
                ConditionTypeEnum conditionType = fieldAnnotation.type();
                String conditionFieldName = fieldAnnotation.fieldName();

                Object value = ReflectionUtil.getValue(criteriaClass, fieldName, criteria);

                if (ObjectUtils.isNotEmpty(value)) {
                    PredicateFactory<E, GenericConditionItem<?>> predicateBuilder = PredicateFactoryRegistry.getBuilder(conditionType);
                    if (ObjectUtils.isNotEmpty(predicateBuilder)) {
                        predicateBuilder.buildPredicate(criteriaBuilder, conditionFieldName, getConditionItem(value, conditionType));
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> GenericConditionItem<T> getConditionItem(T value, ConditionTypeEnum conditionType) {
        Class<?> conditionItemType = conditionType.getConditionItemType();
        try {
            Object object = conditionItemType.getConstructor(Object.class).newInstance(value);
            return (GenericConditionItem<T>) object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Sort.Order> buildSortItems(C criteria, CriteriaBuilder<E> criteriaBuilder) throws DomainException {
        List<Sort.Order> sortItems;
        if (!CollectionUtils.isEmpty(criteria.getSortItems())) {
            sortItems = new ArrayList<>(criteria.getSortItems().size());
            for (SortDTO data : criteria.getSortItems()) {
                if (!getFieldNames().contains(data.getSortBy())) {
                    throw new DomainException(List.of(new ErrorDTO(ErrorCodeEnum.INCONSISTENT_DATA,String.format("Bad sort column name: [%s]" , data.getSortBy()),"Sort")));
                }
                if (data.getSortDirection() == SortDirectionEnum.ASC) {
                    criteriaBuilder.orderByAsc(data.getSortBy());
                    sortItems.add(new Sort.Order(Sort.Direction.ASC, data.getSortBy()));
                } else {
                    criteriaBuilder.orderByDesc(data.getSortBy());
                    sortItems.add(new Sort.Order(Sort.Direction.DESC, data.getSortBy()));
                }
            }
            return sortItems;
        } else if (ObjectUtils.isNotEmpty(criteria.getOffset()) && ObjectUtils.isNotEmpty(criteria.getPageSize())) {
            sortItems = new ArrayList<>(getFieldNames().size());
            for (String entityFieldName : getFieldNames()) {
                Id id = ReflectionUtil.getFieldAnnotation(entityClass, entityFieldName, Id.class);
                if (ObjectUtils.isNotEmpty(id)) {
                    criteriaBuilder.orderByAsc(entityFieldName);
                    sortItems.add(new Sort.Order(Sort.Direction.ASC, entityFieldName));
                } else {
                    EmbeddedId embeddedId = ReflectionUtil.getFieldAnnotation(entityClass, entityFieldName, EmbeddedId.class);
                    if (ObjectUtils.isNotEmpty(embeddedId)) {
                        List<Field> embeddedIdFields = ReflectionUtil.getFields(idClass);
                        for (Field embeddedIdField : embeddedIdFields) {
                            String idFieldName = embeddedIdField.getName();
                            Column idFieldColumn =
                                    ReflectionUtil.getFieldAnnotation(idClass, idFieldName, Column.class);
                            if (ObjectUtils.isNotEmpty(idFieldColumn)) {
                                criteriaBuilder.orderByAsc(idFieldName);
                                sortItems.add(new Sort.Order(Sort.Direction.ASC, idFieldName));
                            }
                        }
                        break;
                    }
                }
            }
            return sortItems;
        }
        return Collections.emptyList();
    }

}
