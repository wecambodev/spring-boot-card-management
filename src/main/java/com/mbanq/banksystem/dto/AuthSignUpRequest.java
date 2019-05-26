package com.mbanq.banksystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value ="AuthSignUpRequest")
public class AuthSignUpRequest {

  @NotNull(message = "Phone Not Allow Null")
  @NotEmpty(message = "Phone Not Allow empty")
  @ApiModelProperty(value = "phone number of auth", required = true, example = "093883292")
  private String phoneNumber;

  @NotNull(message = "password Not Allowed Null")
  @NotEmpty(message = "password Not Allowed Empty")
  @ApiModelProperty(value = "password  of auth", required = true, example = "123456")
  private String password;

  @NotNull(message = "Role Not Allowed Null")
  @NotEmpty(message = "Role Not Allowed Empty")
  @ApiModelProperty(value = "role  of auth", required = true, example = "consumer")
  private String role;


}
