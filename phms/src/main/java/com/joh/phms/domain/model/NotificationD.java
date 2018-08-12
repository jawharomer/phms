package com.joh.phms.domain.model;

public class NotificationD {

	private String title;
	private String message;
	private String etc;

	public static enum NotificationType {
		 WARNING, DANGER,INFO
	}

	private NotificationType notificationType;

	public NotificationD() {

	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	@Override
	public String toString() {
		return "NotificationD [title=" + title + ", message=" + message + ", etc=" + etc + ", notificationType="
				+ notificationType + "]";
	}

}
