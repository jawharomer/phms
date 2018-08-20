package com.joh.phms.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CusDataIntegrityViolationException extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;
	private String customMessage = "";

	public CusDataIntegrityViolationException(String customMessage) {
		super(customMessage);
		this.customMessage = customMessage;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	@Override
	public String toString() {
		return "CusDataIntegrityViolationException [customMessage=" + customMessage + "]";
	}

}
