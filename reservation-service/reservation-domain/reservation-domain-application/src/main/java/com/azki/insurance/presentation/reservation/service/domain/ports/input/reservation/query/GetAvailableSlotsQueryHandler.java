package com.azki.insurance.presentation.reservation.service.domain.ports.input.reservation.query;

import com.azki.insurance.presentation.domain.api.query.PaginatedQueryResult;
import com.azki.insurance.presentation.domain.input.BaseQueryHandler;
import com.azki.insurance.presentation.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.presentation.reservation.service.domain.api.query.GetAvailableSlotsQuery;
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
