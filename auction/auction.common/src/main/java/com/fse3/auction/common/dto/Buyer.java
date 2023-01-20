package com.fse3.auction.common.dto;

import lombok.Data;

@Data
public class Buyer {
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
