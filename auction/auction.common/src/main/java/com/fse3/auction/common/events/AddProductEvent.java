package com.fse3.auction.common.events;

import com.fse3.auction.common.dto.CategoryType;
import com.fse3.auction.common.dto.Seller;
import com.fse3.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddProductEvent extends BaseEvent {
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private CategoryType category;
    private double startPrice;
    private Date bidEndDate;
    private Seller seller;
    private Date createdDate;
}
