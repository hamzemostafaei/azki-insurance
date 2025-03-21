package com.azki.insurance.presentation.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePaginatedEdgeRequestDTO extends BaseEdgeRequestDTO {

    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("sort")
    private List<SortEdgeDTO> sort;
}
