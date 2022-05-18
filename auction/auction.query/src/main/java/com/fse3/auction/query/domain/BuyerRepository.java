package com.fse3.auction.query.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BuyerRepository extends CrudRepository<Buyer, String> {
    Buyer findByEmail(String email);
}
