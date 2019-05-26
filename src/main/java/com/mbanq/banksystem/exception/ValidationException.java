package com.mbanq.banksystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationException extends RuntimeException {

	private Errors errors;

    public ValidationException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

}
