package com.fse3.auction.cmd.api.commands;

import com.fse3.auction.cmd.domain.BidAggregate;
import com.fse3.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidCommandHandler implements CommandHandler{

    @Autowired
    private EventSourcingHandler<BidAggregate> eventSourcingHandler;

    @Override
    public void handle(AddProductCommand command) {
        var aggregate = new BidAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(PlaceBidCommand command) {
        var aggregate = new BidAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(UpdateBidCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.updateBid(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DeleteProductCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.deleteProduct();
        eventSourcingHandler.save(aggregate);
    }
}
