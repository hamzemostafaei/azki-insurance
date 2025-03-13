package com.azki.insurance.presentation.api.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePaginatedEdgeResponseDTO extends BaseEdgeResponseDTO {

    @JsonProperty("hasNext")
    private Boolean hasNext;

    @JsonProperty("recordCount")
    private Long recordCount;

    @JsonIgnore
    private int offset;

    @JsonIgnore
    private int pageSize;

    public boolean getHasNext() {
        if (recordCount == null)
            return false;
        return this.recordCount > (long) (this.offset + 1) * this.pageSize;

    }

}
