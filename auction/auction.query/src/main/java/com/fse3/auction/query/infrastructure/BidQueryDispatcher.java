package com.fse3.auction.query.infrastructure;

import com.fse3.cqrs.core.domain.BaseEntity;
import com.fse3.cqrs.core.infrastructure.QueryDispatcher;
import com.fse3.cqrs.core.queries.BaseQuery;
import com.fse3.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class BidQueryDispatcher implements QueryDispatcher {
    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if(handlers == null || handlers.size() <= 0){
            throw new RuntimeException("No Query handler was registered");
        }
        if(handlers.size() > 1){
            throw new RuntimeException("Cannot send query to more than one handler");
        }
        return handlers.get(0).handle(query);
    }
}
