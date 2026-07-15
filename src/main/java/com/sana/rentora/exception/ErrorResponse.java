package com.sana.rentora.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	private String errorMessage;
	private int status;
	private long timestamp;
}
