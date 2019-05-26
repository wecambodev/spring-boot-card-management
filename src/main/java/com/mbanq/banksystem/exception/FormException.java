package com.mbanq.banksystem.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormException {
	@JsonProperty("status_code")
    private String status_code;
	@JsonProperty("message")
    private String message;
	@JsonProperty("errors")
    private List<FieldException> fieldErrors;

}
