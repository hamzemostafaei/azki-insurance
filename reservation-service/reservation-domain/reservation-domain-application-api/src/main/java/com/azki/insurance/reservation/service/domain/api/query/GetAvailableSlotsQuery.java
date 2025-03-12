package com.azki.insurance.reservation.service.domain.api.query;

import com.azki.insurance.domain.api.query.PaginatedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetAvailableSlotsQuery extends PaginatedQuery {

    private Date startTime;
    private Date endTime;
}
