package com.fse3.auction.query.api.controllers;

import com.fse3.auction.query.api.dto.BidLookupResponse;
import com.fse3.auction.query.api.dto.ProductLookupResponse;
import com.fse3.auction.query.api.queries.FindAllBidsByProductId;
import com.fse3.auction.query.api.queries.FindAllProducts;
import com.fse3.auction.query.api.queries.FindProductInfo;
import com.fse3.auction.query.domain.Buyer;
import com.fse3.auction.query.domain.Product;
import com.fse3.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/showBids")
public class BidLookupController {
    private final Logger logger = Logger.getLogger(BidLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/{id}")
    public ResponseEntity<BidLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            List<Buyer> bids = queryDispatcher.send(new FindAllBidsByProductId(id));
            var product = queryDispatcher.send(new FindProductInfo(id));
            if (bids == null || bids.size() == 0 || product == null || product.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = BidLookupResponse.builder()
                    .bids(bids)
                    .message("Successfully returned all bids!")
                    .product((Product) product.get(0))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get bids by ID request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new BidLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/")
    public ResponseEntity<ProductLookupResponse> getAllProducts(){
        try{
            List<Product> products = queryDispatcher.send(new FindAllProducts());
            if (products == null || products.size() == 0 || products == null || products.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            var response = ProductLookupResponse.builder()
                    .products(products)
                    .message("Successfully returned all products!")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get products request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new ProductLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
