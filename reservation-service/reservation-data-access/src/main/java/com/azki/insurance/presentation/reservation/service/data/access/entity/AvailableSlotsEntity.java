package com.azki.insurance.presentation.reservation.service.data.access.entity;

import com.azki.insurance.presentation.data.access.entity.BaseJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "AVAILABLE_SLOTS", schema = "AZKI_RESERVATION")
public class AvailableSlotsEntity extends BaseJpaEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "is_reserved", nullable = false)
    private Boolean  isReserved;

}
