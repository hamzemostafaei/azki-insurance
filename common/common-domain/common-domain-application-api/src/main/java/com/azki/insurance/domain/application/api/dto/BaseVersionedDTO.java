package com.azki.insurance.domain.application.api.dto;

import com.azki.insurance.common.core.data.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseVersionedDTO extends BaseDTO {
    private Long version;
    private Timestamp creationDate;
    private Long creatorUserId;
    private Timestamp lastUpdate;
    private Long updaterUserId;
}
