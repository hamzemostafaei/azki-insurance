package com.azki.insurance.domain.api.query;

import com.azki.insurance.domain.api.dto.SortDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaginatedQuery extends Query {
    private Integer pageSize;
    private Integer offset;
    private List<SortDTO> sort;
}
