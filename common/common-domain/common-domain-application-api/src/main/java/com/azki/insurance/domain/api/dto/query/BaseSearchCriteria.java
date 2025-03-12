package com.azki.insurance.domain.api.dto.query;

import com.azki.insurance.domain.api.dto.SortDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
