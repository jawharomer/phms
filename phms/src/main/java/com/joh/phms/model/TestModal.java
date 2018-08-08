package com.joh.phms.model;

import java.util.List;

import javax.validation.Valid;

public class TestModal {

	private String firstName;
	private String lastName;

	@Valid
	private List<TestModelSecond> testModelSeconds;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TestModelSecond> getTestModelSeconds() {
		return testModelSeconds;
	}

	public void setTestModelSeconds(List<TestModelSecond> testModelSeconds) {
		this.testModelSeconds = testModelSeconds;
	}

	@Override
	public String toString() {
		return "TestModal [firstName=" + firstName + ", lastName=" + lastName + ", testModelSeconds=" + testModelSeconds
				+ "]";
	}

}
