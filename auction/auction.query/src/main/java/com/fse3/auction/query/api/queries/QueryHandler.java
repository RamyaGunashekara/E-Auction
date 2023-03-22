package com.fse3.auction.query.api.queries;

import com.fse3.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllBidsByProductId query);
    List<BaseEntity> handle(FindProductInfo query);

    List<BaseEntity> handle(FindAllProducts query);
}
