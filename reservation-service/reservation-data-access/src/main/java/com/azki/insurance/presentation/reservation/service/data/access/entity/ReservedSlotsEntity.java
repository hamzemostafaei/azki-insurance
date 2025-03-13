package com.azki.insurance.presentation.reservation.service.data.access.entity;

import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "AVAILABLE_SLOT_ID", nullable = false)
    private Long availableSlotId;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

}
