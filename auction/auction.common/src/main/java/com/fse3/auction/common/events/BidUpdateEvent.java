package com.fse3.auction.common.events;

import com.fse3.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BidUpdateEvent extends BaseEvent {
    private String productId;
    private String buyerId;
    private String email;
    private double amount;
}
