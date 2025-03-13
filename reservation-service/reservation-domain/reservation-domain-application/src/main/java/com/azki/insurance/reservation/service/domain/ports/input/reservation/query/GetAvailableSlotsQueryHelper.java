package com.azki.insurance.reservation.service.domain.ports.input.reservation.query;

import com.azki.insurance.domain.api.dto.SortDTO;
import com.azki.insurance.domain.api.dto.SortDirectionEnum;
import com.azki.insurance.domain.api.query.PaginatedQueryResult;
import com.azki.insurance.reservation.service.domain.api.dto.AvailableSlotsDTO;
import com.azki.insurance.reservation.service.domain.api.dto.search.AvailableSlotsCriteriaDTO;
import com.azki.insurance.reservation.service.domain.api.query.GetAvailableSlotsQuery;
import com.azki.insurance.reservation.service.domain.ports.output.AvailableSlotsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAvailableSlotsQueryHelper {

    private final AvailableSlotsRepository availableSlotsRepository;

    @Cacheable(value = "available_slots", keyGenerator = "customKeyGenerator")
    public PaginatedQueryResult<AvailableSlotsDTO> getAvailableSlotsDTOPaginatedQueryResult(GetAvailableSlotsQuery query) {
        AvailableSlotsCriteriaDTO criteria = new AvailableSlotsCriteriaDTO();
        criteria.setStartTimeGreaterThanOrEqual(query.getStartTime());
        criteria.setEndTimeLessThanOrEqual(query.getEndTime());
        criteria.setIsReserved(false);
        criteria.setPageSize(query.getPageSize());
        criteria.setOffset(query.getOffset());
        criteria.setReturnTotalSize(true);
        SortDTO orderBy = new SortDTO();
        orderBy.setSortBy("startTime");
        orderBy.setSortDirection(SortDirectionEnum.ASC);
        criteria.setSortItems(List.of(orderBy));

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
