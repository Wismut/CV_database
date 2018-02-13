package ru.javawebinar.webapp.model;

import java.util.Objects;

public class Link {
	public static Link EMPTY = new Link();
	final private String name;
	final private String url;

	public Link() {
		this("", null);
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Link(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public Link(Link link) {
		this(link.name, link.url);
	}

	public static Link empty() {
		return EMPTY;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Link link = (Link) o;
		return Objects.equals(name, link.name) &&
				Objects.equals(url, link.url);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, url);
	}

	@Override
	public String toString() {
		return "Link{" +
				"name='" + name + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
