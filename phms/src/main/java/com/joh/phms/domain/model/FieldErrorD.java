package com.joh.phms.domain.model;

public class FieldErrorD {

	private String field;

	private String message;

	public FieldErrorD(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FieldErrorD [field=" + field + ", message=" + message + "]";
	}

}