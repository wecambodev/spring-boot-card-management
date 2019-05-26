package com.mbanq.banksystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long id;
    private String phoneNumber;
    @JsonIgnore
    private String password;
    private String role;
    private Timestamp createdAt;
    private Integer status;

    @JsonProperty
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss",timezone = "GMT+7")
    public Timestamp getCreatedAt() {
        return createdAt;
    }


}