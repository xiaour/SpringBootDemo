package com.github.xiaour.exception;

import java.io.IOException;

public class OApiException extends Exception {

	/**
	 */
	private static final long serialVersionUID = 1L;

	public OApiException(int errCode, String errMsg) {
		super("error code: " + errCode + ", error message: " + errMsg);
	}

	public OApiException(IOException e) {
		super(e);
	}
}
