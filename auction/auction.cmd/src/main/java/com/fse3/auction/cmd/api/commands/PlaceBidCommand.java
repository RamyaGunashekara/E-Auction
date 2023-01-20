package com.fse3.auction.cmd.api.commands;

import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class PlaceBidCommand extends BaseCommand {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int pin;
    private int phone;
    private String email;
    private double amount;
    private String productId;
}
