package com.azki.insurance.reservation.service.data.access.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ReservedSlotsEntityId {

    @NotNull
    @Column(name = "AVAILABLE_SLOT_ID", nullable = false)
    private Integer availableSlotId;

    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;
}
