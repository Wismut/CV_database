package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class TextSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private String value;

	public TextSection() {
	}

	public TextSection(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TextSection that = (TextSection) o;
		return Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
