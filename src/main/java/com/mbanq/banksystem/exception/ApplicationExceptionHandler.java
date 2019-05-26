package com.mbanq.banksystem.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		ValidationException ire = (ValidationException) e;

		List<FieldException> fieldErrorResources = new ArrayList<>();

		List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			FieldException fieldErrorResource = new FieldException();
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}

		FormException error = new FormException();
		error.setStatus_code("400");
		error.setMessage(e.getMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}


	@ExceptionHandler({ MethodNotAllowedException.class })
	public ResponseEntity<Object> handleMethodNotAllowed(Exception ex, WebRequest request) {

		return new ResponseEntity<>(" Method Not Allow "
				, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}


	@ExceptionHandler({ ApiError.class })
	public ResponseEntity<Object> handleApiError(Exception ex, WebRequest request) {

		HashMap response = new HashMap();
		response.put("status_code", "500");
		response.put("errors", ex.getStackTrace());
		response.put("message", ex.getMessage());

		return new ResponseEntity<>(response , new HttpHeaders(), HttpStatus.FORBIDDEN);
	}


	@Bean
	public ErrorAttributes errorAttributes() {
		// Hide exception field in the return object
		return new DefaultErrorAttributes() {


		};
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> accessDeniedExceptionHandler(RuntimeException ex, WebRequest request) {

		HashMap response = new HashMap();
		response.put("status_code", "500");
		response.put("errors", ex.getStackTrace());
		response.put("message", ex.getMessage());

		return new ResponseEntity<>(response , new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> exceptionHandle(Exception ex, WebRequest request) {

		HashMap response = new HashMap();
		response.put("status_code", "500");
		response.put("errors", ex.getStackTrace());
		response.put("message", ex.getMessage());

		return new ResponseEntity<>(response , new HttpHeaders(), HttpStatus.FORBIDDEN);
	}


	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}



}
