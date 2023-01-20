package com.fse3.auction.query.infrastructure.consumers;

import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;
import com.fse3.auction.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer implements EventConsumer{

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics = "AddProductEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AddProductEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "PlaceBidEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload PlaceBidEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
    @KafkaListener(topics = "BidUpdateEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload BidUpdateEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics = "DeleteProductEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload DeleteProductEvent event, Acknowledgment ack) {
        this.eventHandler.on(event);
        ack.acknowledge();
    }
}
