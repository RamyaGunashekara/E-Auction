package com.fse3.auction.query.infrastructure.consumers;

import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AddProductEvent event, Acknowledgment ack);
    void consume(@Payload PlaceBidEvent event, Acknowledgment ack);
    void consume(@Payload DeleteProductEvent event, Acknowledgment ack);
    void consume(@Payload BidUpdateEvent event, Acknowledgment ack);
}
