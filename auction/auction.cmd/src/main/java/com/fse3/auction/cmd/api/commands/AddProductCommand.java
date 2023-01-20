package com.fse3.auction.cmd.api.commands;

import com.fse3.auction.common.dto.CategoryType;
import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddProductCommand extends BaseCommand {
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private CategoryType category;
    private double startPrice;
    private LocalDate bidEndDate;
    private LocalDate createdDate;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    private String email;
}
