package com.fse3.auction.query.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, String> {
    Optional<Buyer> findByEmail(String email);
    List<Buyer> findByProductId(String product_id, Sort sort);
}
