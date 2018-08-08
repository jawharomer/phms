package com.joh.phms.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorD {

	private List<FieldErrorD> fieldErrors = new ArrayList<>();

	public ValidationErrorD() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorD error = new FieldErrorD(path, message);
		fieldErrors.add(error);
	}

	public List<FieldErrorD> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorD> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	@Override
	public String toString() {
		return "ValidationErrorD [fieldErrors=" + fieldErrors + ", getFieldErrors()=" + getFieldErrors()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}