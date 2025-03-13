package com.azki.insurance.domain.application.api.input;

import com.azki.insurance.domain.application.api.query.Query;
import com.azki.insurance.domain.application.api.query.QueryResult;

public interface QueryHandler<Q extends Query, R extends QueryResult<?>> {
    R handle(Q query);
}
