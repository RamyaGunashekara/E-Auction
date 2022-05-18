package com.fse3.auction.cmd.domain;

import com.fse3.auction.cmd.api.commands.AddProductCommand;
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
    private String buyerId;
    private String productId;

    public BidAggregate(AddProductCommand command){
        raiseEvent(AddProductEvent.builder()
                .id(command.getId())
                .productName(command.getProductName())
                .createdDate(new Date())
                .category(command.getCategory())
                .startPrice(command.getStartPrice())
                .bidEndDate(command.getBidEndDate())
                .build());
    }

    public void apply(AddProductEvent event){
        this.id = event.getId();
        this.active = true;
        this.amount = event.getStartPrice();
    }

    public void placeBids(String buyerId, String productId, String email, double amount){
        if(!this.active){
            throw new IllegalStateException("Bids cannot be placed on already sold Product");
        }
        if(amount <= 0){
            throw new IllegalStateException("The bid can't be less than or equal to 0");
        }
        raiseEvent(PlaceBidEvent.builder()
                .id(this.id)
                .productId(this.productId)
                .buyerId(this.buyerId)
                .email(this.email)
                .amount(this.amount)
                .build());
    }

    public void apply(PlaceBidEvent event){
        this.id = event.getId();
        this.buyerId = event.getBuyerId();
        this.productId = event.getProductId();
        this.email = event.getEmail();
        this.amount = event.getAmount();
    }

    public void updateBid(String buyerId, String productId, String email, double amount){
        if(!this.active){
            throw new IllegalStateException("Bids cannot be placed on already sold Product");
        }
        raiseEvent(BidUpdateEvent.builder()
                .id(this.id)
                .productId(this.productId)
                .buyerId(this.buyerId)
                .email(this.email)
                .amount(this.amount)
                .build());
    }

    public void apply(BidUpdateEvent event){
        this.id = event.getId();
        this.buyerId = event.getBuyerId();
        this.productId = event.getProductId();
        this.email = event.getEmail();
        this.amount = event.getAmount();
    }

    public void deleteProduct(){
        if(!this.active){
            throw new IllegalStateException("Product already deleted");
        }

        raiseEvent(DeleteProductEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(DeleteProductEvent event){
        this.id = event.getId();
        this.active = false;
    }
}
