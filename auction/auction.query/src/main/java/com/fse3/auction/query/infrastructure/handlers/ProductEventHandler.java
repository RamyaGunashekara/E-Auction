package com.fse3.auction.query.infrastructure.handlers;

import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;
import com.fse3.auction.query.domain.Buyer;
import com.fse3.auction.query.domain.BuyerRepository;
import com.fse3.auction.query.domain.Product;
import com.fse3.auction.query.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEventHandler implements EventHandler{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public void on(AddProductEvent event) {
        var product = Product.builder()
                .id(event.getId())
                .productName(event.getProductName())
                .category(event.getCategory())
                .createdDate(event.getCreatedDate())
                .shortDescription(event.getShortDescription())
                .detailedDescription(event.getDetailedDescription())
                .bidEndDate(event.getBidEndDate())
                .startPrice(event.getStartPrice())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .address(event.getAddress())
                .city(event.getCity())
                .state(event.getState())
                .pin(event.getPin())
                .phone(event.getPhone())
                .email(event.getEmail())
                .build();
        productRepository.save(product);


    }

    @Override
    public void on(BidUpdateEvent event) {
        var buyer = buyerRepository.findById(event.getId());
        if(buyer.isEmpty()){
            return;
        }
        buyer.get().setEmail(event.getEmail());
        buyer.get().setAmount(event.getAmount());
        buyerRepository.save(buyer.get());
    }



    @Override
    public void on(PlaceBidEvent event) {
        var buyer = Buyer.builder()
                .id(event.getId())
                .productId(event.getProductId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .address(event.getAddress())
                .city(event.getCity())
                .state(event.getState())
                .pin(event.getPin())
                .phone(event.getPhone())
                .email(event.getEmail())
                .amount(event.getAmount())
                .build();
        buyerRepository.save(buyer);
    }

    @Override
    public void on(DeleteProductEvent event) {
        productRepository.deleteById(event.getId());
    }
}
