package com.fse3.auction.query.api.queries;

import com.fse3.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAllBidsByProductId extends BaseQuery {
    private String product_id;

}
