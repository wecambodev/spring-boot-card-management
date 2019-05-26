package com.mbanq.banksystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value ="AuthSignInRequest")
public class AuthSignInRequest {

  @NotNull(message = "Number Phone Not Null")
  @NotEmpty( message = "Number Phone Not Empty")

  @ApiModelProperty(value = "phone number of auth", required = true, example = "093883292")
  private String phoneNumber;

  @NotNull(message = "password Not Null")
  @ApiModelProperty(value = "password  of auth", required = true, example = "123456")
  private String password;



}
