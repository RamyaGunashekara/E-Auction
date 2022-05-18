package com.fse3.auction.cmd.api.commands;

import com.fse3.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DeleteProductCommand extends BaseCommand {
    public DeleteProductCommand(String id){
        super(id);
    }
}
