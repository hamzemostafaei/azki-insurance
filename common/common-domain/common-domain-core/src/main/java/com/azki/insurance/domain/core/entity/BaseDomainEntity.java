package com.azki.insurance.domain.core.entity;

import com.azki.insurance.domain.core.valueobject.BaseId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode
public abstract class BaseDomainEntity<ID extends BaseId<?>> {
    private ID id;
}
