package com.mbanq.banksystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Positive;

@Data
@ApiModel(value ="CardDeactivateRequest")
public class CardDeactivateRequest {

  @Positive(message = "Id Must be positive")
  @ApiModelProperty(value = "Id of card", required = true)
  private Long id;



}
