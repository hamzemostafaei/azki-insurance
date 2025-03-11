package com.azki.insurance.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SortEdgeDTO {

    @JsonProperty("sortBy")
    private String sortBy;

    @JsonProperty("sortDirection")
    private SortDirectionEnum sortDirection = SortDirectionEnum.ASC;

}
