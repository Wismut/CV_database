package ru.javawebinar.webapp.model;

import java.io.Serializable;

public enum SectionType implements Serializable {
	OBJECTIVE("Позиция"),
	ACHIEVEMENT("Достижения"),
	QUALIFICATIONS("Квалификация"),
	EXPERIENCE("Опыт работы"),
	EDUCATION("Образование");

	static final long serialVersionUID = 1L;

	private String type;

	SectionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
