package com.fse3.auction.cmd.infrastructure;

import com.fse3.auction.cmd.domain.BidAggregate;
import com.fse3.auction.cmd.domain.EventStoreRepository;
import com.fse3.cqrs.core.events.BaseEvent;
import com.fse3.cqrs.core.events.EventModel;
import com.fse3.cqrs.core.exceptions.AggregateNotFoundException;
import com.fse3.cqrs.core.exceptions.ConcurrencyException;
import com.fse3.cqrs.core.infrastructure.EventStore;
import com.fse3.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductEventStore implements EventStore {

    @Autowired
    private EventProducer eventProducer;
    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .id(event.getId())
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(BidAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect product ID provided!");
        }
        return eventStream.stream().map(x -> x.getEventData()).collect(Collectors.toList());
    }
}
