package com.azki.insurance.reservation.service.domain.ports.input.reservation.query;

import com.azki.insurance.domain.api.query.PaginatedQueryResult;
import com.azki.insurance.domain.input.BaseQueryHandler;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.api.query.SearchReservations;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class GetReservationsQueryHandler extends BaseQueryHandler<SearchReservations, PaginatedQueryResult<AvailableSlotsDTO>> {

    private final AvailableSlotsRepository availableSlotsRepository;

    @Override
    protected PaginatedQueryResult<AvailableSlotsDTO> execute(SearchReservations query) {

        AvailableSlotsCriteriaDTO criteria = new AvailableSlotsCriteriaDTO();
        criteria.setStartTimeGreaterThanOrEqual(query.getStartTime());
        criteria.setEndTimeLessThanOrEqual(query.getEndTime());
        criteria.setIsReserved(BooleanUtils.toBooleanDefaultIfNull(query.getIsReserved(), false));
        criteria.setPageSize(query.getPageSize());
        criteria.setOffset(query.getOffset());
        criteria.setReturnTotalSize(true);

        Page<AvailableSlotsDTO> resultPage = availableSlotsRepository.search(criteria);
        List<AvailableSlotsDTO> content = resultPage.getContent();

        PaginatedQueryResult<AvailableSlotsDTO> result = new PaginatedQueryResult<>();
        result.setRecordCount(resultPage.getTotalElements());
        result.setPageSize(resultPage.getSize());
        result.setOffset(resultPage.getNumber());
        result.setData(content);

        return result;
    }
}
