package com.mbanq.banksystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@ApiModel(value ="CardDailyLimitRequest")
public class CardDailyLimitRequest {

  @Positive(message = "Id Must be positive")
  @ApiModelProperty(value = "id  of card", required = true)
  private Long id;

  @NotEmpty(message = "Phone Not Allow empty")
  @ApiModelProperty(value = "Daily limit of card", required = true, example = "1000.00")
  private Double dailyLimit;



}
