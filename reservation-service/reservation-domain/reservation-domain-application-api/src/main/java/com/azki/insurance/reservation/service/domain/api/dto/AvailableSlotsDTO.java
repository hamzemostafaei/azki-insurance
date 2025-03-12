package com.azki.insurance.reservation.service.domain.api.dto;

import com.azki.insurance.domain.api.dto.BaseVersionedDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AvailableSlotsDTO extends BaseVersionedDTO {

    private Long id;
    private Date startTime;
    private Date endTime;
    private Boolean isReserved;

}
