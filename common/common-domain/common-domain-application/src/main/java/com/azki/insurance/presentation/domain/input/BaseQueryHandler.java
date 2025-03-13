package com.azki.insurance.presentation.domain.input;

import com.azki.insurance.presentation.common.app.config.SearchCommonConfigData;
import com.azki.insurance.presentation.domain.api.input.QueryHandler;
import com.azki.insurance.presentation.domain.api.query.PaginatedQuery;
import com.azki.insurance.presentation.domain.api.query.Query;
import com.azki.insurance.presentation.domain.api.query.QueryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public abstract class BaseQueryHandler<Q extends Query, R extends QueryResult<?>> implements QueryHandler<Q, R> {

    @Autowired
    private SearchCommonConfigData commonConfig;

    @Override
    public final R handle(Q query) {
        logDebug("Handling query", query);

        validateAndSetPagination(query);

        R result = execute(query);

        logDebug("Query handling completed", query);

        return result;
    }

    protected abstract R execute(Q query);

    private void validateAndSetPagination(Q query) {
        if (query instanceof PaginatedQuery paginatedQuery) {
            Integer pageSize = paginatedQuery.getPageSize();
            Integer offset = paginatedQuery.getOffset();

            if (pageSize == null || pageSize <= 0 || pageSize > commonConfig.pageSize()) {
                paginatedQuery.setPageSize(commonConfig.pageSize());
            }

            if (offset == null || offset < 0) {
                paginatedQuery.setOffset(0);
            }
        }
    }

    private void logDebug(String message, Q query) {
        if (log.isDebugEnabled()) {
            log.debug("{} [{}]", message, query);
        }
    }
}