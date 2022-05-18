package com.fse3.cqrs.core.producers;

import com.fse3.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
