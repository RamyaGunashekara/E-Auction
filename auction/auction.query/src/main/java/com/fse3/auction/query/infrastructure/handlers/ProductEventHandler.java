package com.fse3.auction.query.infrastructure.handlers;

import com.fse3.auction.common.events.AddProductEvent;
import com.fse3.auction.common.events.BidUpdateEvent;
import com.fse3.auction.common.events.DeleteProductEvent;
import com.fse3.auction.common.events.PlaceBidEvent;
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
                .build();
        productRepository.save(product);
    }

    @Override
    public void on(BidUpdateEvent event) {
        var product = productRepository.findById(event.getProductId());
        if(product.isEmpty()){
            return;
        }
        product.get().setBuyerId(event.getBuyerId());
        product.get().setEmail(event.getEmail());
        product.get().setAmount(event.getAmount());
        productRepository.save(product.get());
    }

    @Override
    public void on(DeleteProductEvent event) {
        productRepository.deleteById(event.getId());
    }

    @Override
    public void on(PlaceBidEvent event) {
        var product = productRepository.findById(event.getProductId());
        if(product.isEmpty()){
            return;
        }
        product.get().setBuyerId(event.getBuyerId());
        product.get().setEmail(event.getEmail());
        product.get().setAmount(event.getAmount());
        productRepository.save(product.get());
    }
}
