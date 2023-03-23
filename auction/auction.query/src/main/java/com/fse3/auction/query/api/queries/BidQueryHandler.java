package com.fse3.auction.query.api.queries;

import com.fse3.auction.query.domain.Buyer;
import com.fse3.auction.query.domain.BuyerRepository;
import com.fse3.auction.query.domain.Product;
import com.fse3.auction.query.domain.ProductRepository;
import com.fse3.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BidQueryHandler implements QueryHandler{

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<BaseEntity> handle(FindAllBidsByProductId query) {
        Iterable<Buyer> bids = buyerRepository.findByProductId(query.getProduct_id(), Sort.by(Sort.Direction.DESC, "amount"));
        List<BaseEntity> bidsList = new ArrayList<>();
        bids.forEach(bidsList::add);
        return bidsList;
    }

    @Override
    public List<BaseEntity> handle(FindProductInfo query) {
        var product = productRepository.findById(query.getId());
        if (product.isEmpty()) {
            return null;
        }
        List<BaseEntity> productList = new ArrayList<>();
        productList.add(product.get());
        return productList;
    }

    @Override
    public List<BaseEntity> handle(FindAllProducts query) {
        Iterable<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, "productName"));
        List<BaseEntity> productList = new ArrayList<>();
        products.forEach(productList::add);
        return productList;
    }

}
