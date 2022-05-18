package com.fse3.auction.query.domain;

import com.fse3.auction.common.dto.CategoryType;
import com.fse3.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product extends BaseEntity {
    @Id
    private String id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private CategoryType category;
    private double startPrice;
    private Date bidEndDate;
    private Date createdDate;
    private String buyerId;
    private String email;
    private double amount;
}
