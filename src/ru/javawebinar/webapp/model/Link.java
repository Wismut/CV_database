package ru.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
	static final long serialVersionUID = 1L;

	public static Link EMPTY = new Link();
	final private String name;
	final private String url;

	public Link() {
		this("", "");
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Link(String name, String url) {
		Objects.requireNonNull(name, "name is null");
		this.name = name;
		this.url = url == null ? "" : url;
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
