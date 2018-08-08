package com.joh.phms.domain.model;

public class JsonResponse {

	private int status;
	private String message;
	private String etc;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "JsonResponse [status=" + status + ", message=" + message + ", etc=" + etc + "]";
	}

}
