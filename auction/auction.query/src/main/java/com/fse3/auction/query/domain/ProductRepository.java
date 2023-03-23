package com.fse3.auction.query.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {
    Optional<Product> findById(String id);

    Iterable<Product> findAll(Sort productName);
}
