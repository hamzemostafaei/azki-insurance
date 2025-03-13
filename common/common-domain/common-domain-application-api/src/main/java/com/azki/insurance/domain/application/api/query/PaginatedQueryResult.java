package com.azki.insurance.domain.application.api.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaginatedQueryResult<D> extends QueryResult<List<D>> {

    private Boolean hasNext;
    private Long recordCount;
    private int offset;
    private int pageSize;

    public boolean getHasNext() {
        if (recordCount == null) {
            return false;
        }
        return this.recordCount > (long) (this.offset + 1) * this.pageSize;
    }
}
