package com.fse3.auction.query.infrastructure.handlers;

import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;

public interface EventHandler {
    void on(AddProductEvent event);
    void on(BidUpdateEvent event);
    void on(DeleteProductEvent event);
    void on(PlaceBidEvent event);
}
