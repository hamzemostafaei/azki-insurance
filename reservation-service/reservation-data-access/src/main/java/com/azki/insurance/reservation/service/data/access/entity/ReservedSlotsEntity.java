package com.azki.insurance.reservation.service.data.access.entity;

import com.azki.insurance.data.access.entity.BaseJpaEntity;
import com.azki.insurance.reservation.service.data.access.entity.id.ReservedSlotsEntityId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "RESERVED_SLOTS", schema = "AZKI_RESERVATION")
public class ReservedSlotsEntity extends BaseJpaEntity {

    @EmbeddedId
    private ReservedSlotsEntityId reservedSlotsId;

}
