package com.fse3.auction.cmd.api.dto;

import com.fse3.auction.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductResponse extends BaseResponse {
    private String id;

    public AddProductResponse(String message, String id){
        super(message);
        this.id = id;
    }
}
