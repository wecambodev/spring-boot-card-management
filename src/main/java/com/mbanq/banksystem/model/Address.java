package com.mbanq.banksystem.model;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private String address;
    private String district;
    private String city;
    private String postalCode;
    private String country;
}
