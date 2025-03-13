package com.azki.insurance.presentation.domain.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortDTO {

    private String sortBy;

    private SortDirectionEnum sortDirection = SortDirectionEnum.ASC;
}
