package com.azki.insurance.reservation.service.domain.api.query;

import com.azki.insurance.domain.api.query.PaginatedQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchReservations extends PaginatedQuery {

    private Date startTime;
    private Date endTime;
    private Boolean isReserved;
}
