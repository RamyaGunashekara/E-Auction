package com.fse3.auction.cmd.api.commands;

import com.fse3.auction.common.dto.CategoryType;
import com.fse3.auction.common.dto.Seller;
import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.util.Date;

@Data
public class AddProductCommand extends BaseCommand {
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private CategoryType category;
    private double startPrice;
    private Date bidEndDate;
    private Seller seller;
}
