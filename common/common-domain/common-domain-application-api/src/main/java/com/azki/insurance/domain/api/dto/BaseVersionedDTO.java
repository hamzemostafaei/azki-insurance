package com.azki.insurance.domain.api.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@ToString
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BaseVersionedDTO {
    private Long version;
    private Timestamp creationDate;
    private Long creatorUserId;
    private Timestamp lastUpdate;
    private Long updaterUserId;
}
