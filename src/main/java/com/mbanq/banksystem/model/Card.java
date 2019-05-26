package com.mbanq.banksystem.model;
import lombok.Data;

import java.sql.Date;

@Data
public class Card {
    private Long id;
    private Address address;
    private User    consumer;
    private String cardNumber;
    private String holderName;
    private String cardType; // 1: VISA Card, 2: Master Card
    private Date expiredDate;
    private String csv;
    private Double dailyLimit;
    private Boolean status;
}
