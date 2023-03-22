package com.fse3.auction.query.api.dto;

import com.fse3.auction.common.dto.BaseResponse;
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
public class ProductLookupResponse extends BaseResponse {
    private List<Product> products;
    public ProductLookupResponse(String message){
        super(message);
    }
}
