package com.azki.insurance.domain.application.api.dto.query;

import com.azki.insurance.domain.application.api.dto.SortDTO;

import java.util.List;

public interface SearchCriteria {

    Integer getOffset();

    Integer getPageSize();

    List<SortDTO> getSortItems();

    Boolean getReturnTotalSize();
}
