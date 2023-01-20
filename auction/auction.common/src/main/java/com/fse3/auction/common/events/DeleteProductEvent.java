package com.fse3.auction.common.events;

import com.fse3.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class DeleteProductEvent extends BaseEvent {
}
