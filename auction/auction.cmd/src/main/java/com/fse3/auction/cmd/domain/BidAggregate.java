package com.fse3.auction.cmd.domain;

import com.fse3.auction.cmd.api.commands.AddProductCommand;
import com.fse3.auction.cmd.api.commands.PlaceBidCommand;
import com.fse3.auction.cmd.api.commands.UpdateBidCommand;
import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;
import com.fse3.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class BidAggregate extends AggregateRoot {
    private Boolean active;
    private double amount;
    private String email;

    public BidAggregate(AddProductCommand command){
        raiseEvent(AddProductEvent.builder()
                .id(command.getId())
                .productName(command.getProductName())
                .shortDescription(command.getShortDescription())
                .detailedDescription(command.getDetailedDescription())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(command.getAddress())
                .city(command.getCity())
                .state(command.getState())
                .pin(command.getPin())
                .phone(command.getPhone())
                .email(command.getEmail())
                .createdDate(new Date())
                .category(command.getCategory())
                .startPrice(command.getStartPrice())
                .bidEndDate(command.getBidEndDate())
                .build());
    }

    public void apply(AddProductEvent event){
        this.id = event.getId();
        this.active = true;
    }

    public BidAggregate(PlaceBidCommand command){
        raiseEvent(PlaceBidEvent.builder()
                .id(command.getId())
                .productId(command.getProductId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .address(command.getAddress())
                .city(command.getCity())
                .state(command.getState())
                .pin(command.getPin())
                .phone(command.getPhone())
                .email(command.getEmail())
                .amount(command.getAmount())
                .build());
    }

    public void apply(PlaceBidEvent event){
        this.id = event.getId();
        this.active = true;
    }

    public void updateBid(UpdateBidCommand command){
        if(!this.active){
            throw new IllegalStateException("Bids cannot be placed on already sold Product");
        }
        raiseEvent(BidUpdateEvent.builder()
                .id(command.getId())
                .email(command.getEmail())
                .amount(command.getAmount())
                .build());
    }

    public void apply(BidUpdateEvent event){
        this.id = event.getId();
        this.email = event.getEmail();
        this.amount = event.getAmount();
        this.active = true;
    }

    public void deleteProduct(){
        if(!this.active){
            throw new IllegalStateException("Product already deleted");
        }

        raiseEvent(DeleteProductEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(DeleteProductEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
