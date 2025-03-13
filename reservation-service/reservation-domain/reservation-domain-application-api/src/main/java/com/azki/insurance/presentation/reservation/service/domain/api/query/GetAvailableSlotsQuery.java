package com.azki.insurance.presentation.reservation.service.domain.api.query;

import com.azki.insurance.presentation.domain.api.query.PaginatedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAvailableSlotsQuery extends PaginatedQuery {

    private Date startTime;
    private Date endTime;
}
