package com.joh.phms.model;

import javax.validation.constraints.NotNull;

public class TestModelSecond {
	private String gifts;
	@NotNull(message = "why second is null?")
	private String second;

	public String getGifts() {
		return gifts;
	}

	public void setGifts(String gifts) {
		this.gifts = gifts;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "TestModelSecond [gifts=" + gifts + ", second=" + second + "]";
	}

}
