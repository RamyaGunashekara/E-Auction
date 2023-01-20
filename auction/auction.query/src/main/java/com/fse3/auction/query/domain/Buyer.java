package com.fse3.auction.query.domain;

import com.fse3.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Buyer extends BaseEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int pin;
    private int phone;
    private String email;
    private double amount;
    private String productId;
}
