package com.fse3.auction.cmd.api.commands;

import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class UpdateBidCommand extends BaseCommand {
   private double amount;
   private String email;
}
