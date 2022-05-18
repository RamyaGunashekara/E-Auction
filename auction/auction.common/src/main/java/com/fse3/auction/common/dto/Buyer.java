package com.fse3.auction.common.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Buyer {
    @Id
    private int id;
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
