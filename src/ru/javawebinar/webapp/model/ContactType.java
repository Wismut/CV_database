package ru.javawebinar.webapp.model;

public enum ContactType {
	PHONE("Тел."),
	MOBILE("Мобильный"),
	HOME_PHONE("Домашний тел."),
	SKYPE("Skype"),
	MAIL("Почта"),
	ICQ("ICQ");

	private String title;

	ContactType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
