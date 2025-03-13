package com.azki.insurance.reservation.service.domain.application.reservation.input.query;

import com.azki.insurance.domain.application.api.query.PaginatedQueryResult;
import com.azki.insurance.domain.application.input.BaseQueryHandler;
import com.azki.insurance.reservation.service.domain.application.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.application.api.query.GetAvailableSlotsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class GetAvailableSlotsQueryHandler extends BaseQueryHandler<GetAvailableSlotsQuery, PaginatedQueryResult<AvailableSlotsDTO>> {

    private final GetAvailableSlotsQueryHelper getAvailableSlotsQueryHelper;

    @Override
    protected PaginatedQueryResult<AvailableSlotsDTO> execute(GetAvailableSlotsQuery query) {
        return getAvailableSlotsQueryHelper.getAvailableSlotsDTOPaginatedQueryResult(query);
    }

}
