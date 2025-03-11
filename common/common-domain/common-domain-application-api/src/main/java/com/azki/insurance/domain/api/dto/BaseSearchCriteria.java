package com.azki.insurance.domain.api.dto;

import com.azki.insurance.domain.api.dto.query.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseSearchCriteria implements SearchCriteria {
    protected Integer offset;
    protected Integer pageSize;
    protected List<SortDTO> sortItems;
    protected Boolean returnTotalSize;

    public void setSortItemsFromEdge(List<SortDTO> sortItems) {
        if (!CollectionUtils.isEmpty(sortItems)) {
            this.sortItems = sortItems
                    .stream()
                    .map(sortEdgeDTO -> {
                        SortDTO sort = new SortDTO();
                        sort.setSortBy(sortEdgeDTO.getSortBy());
                        sort.setSortDirection(sortEdgeDTO.getSortDirection());
                        return sort;
                    })
                    .collect(Collectors.toList());
        }
    }
}
