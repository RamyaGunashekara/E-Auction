package com.fse3.auction.cmd.api.commands;

public interface CommandHandler {

    void handle(AddProductCommand command);
    void handle(PlaceBidCommand command);
    void handle(UpdateBidCommand command);
    void handle(DeleteProductCommand command);

}
