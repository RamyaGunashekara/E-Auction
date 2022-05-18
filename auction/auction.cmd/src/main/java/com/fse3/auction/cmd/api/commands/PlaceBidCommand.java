package com.fse3.auction.cmd.api.commands;

import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class PlaceBidCommand extends BaseCommand {
    private String buyerId;
    private String productId;
    private double amount;
    private String email;
}