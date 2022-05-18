package com.fse3.auction.common.events;

import com.fse3.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class DeleteProductEvent extends BaseEvent {
}
