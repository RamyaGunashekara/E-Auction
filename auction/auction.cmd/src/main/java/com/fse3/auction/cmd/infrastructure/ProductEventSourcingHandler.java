package com.fse3.auction.cmd.infrastructure;

import com.fse3.auction.cmd.domain.BidAggregate;
import com.fse3.cqrs.core.domain.AggregateRoot;
import com.fse3.cqrs.core.handlers.EventSourcingHandler;
import com.fse3.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class ProductEventSourcingHandler implements EventSourcingHandler<BidAggregate> {

    @Autowired
    private EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public BidAggregate getById(String id) {
        var aggregate = new BidAggregate();
        var events = eventStore.getEvents(id);
        if(events != null && !events.isEmpty()){
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(x -> x.getVersion()).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
