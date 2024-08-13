package com.ecommerce.exceptions;

public class ErrorResponse {
	
	private String errorMessage;
	private String errorCode;
	
	public ErrorResponse() {}

	public ErrorResponse(String errorMessage, String errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return errorMessage;
	}

	public void setMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCode() {
		return errorCode;
	}

	public void setCode(String errorCode) {
		this.errorCode = errorCode;
	}
		
}
