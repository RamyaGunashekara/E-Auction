package com.fse3.auction.query.api.dto;

import com.fse3.auction.common.dto.BaseResponse;
import com.fse3.auction.query.domain.Buyer;
import com.fse3.auction.query.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BidLookupResponse extends BaseResponse {
    private List<Buyer> bids;

    private Product product;

    public BidLookupResponse(String message){
        super(message);
    }
}
