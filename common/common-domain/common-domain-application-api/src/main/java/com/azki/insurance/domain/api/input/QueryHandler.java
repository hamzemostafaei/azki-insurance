package com.azki.insurance.domain.api.input;

import com.azki.insurance.domain.api.query.Query;
import com.azki.insurance.domain.api.query.QueryResult;

public interface QueryHandler<Q extends Query, R extends QueryResult<?>> {
    R handle(Q query);
}
