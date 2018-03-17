package ru.javawebinar.webapp.model;


import java.util.*;

public class Resume {//implements Comparable<Resume> {
	private String uuid;
	private String fullName;
	private String location;
	private String homePage;
	private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
	private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

	public Resume(String fullName, String location) {
		this(UUID.randomUUID().toString(), fullName, location);
	}

	public Resume() {
		uuid = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Resume resume = (Resume) o;
		return Objects.equals(uuid, resume.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	public Resume(String uuid, String fullName, String location) {
		this.uuid = uuid;
		this.fullName = fullName;
		this.location = location;
	}

	public void addSection(SectionType type, Section section) {
		sections.put(type, section);
	}

	public void addContact(ContactType type, String value) {
		contacts.put(type, value);
	}

	public String getUuid() {
		return uuid;
	}

	public String getFullName() {
		return fullName;
	}

	public String getLocation() {
		return location;
	}

	public String getHomePage() {
		return homePage;
	}

	public String getContact(ContactType type) {
		return contacts.get(type);
	}

	public Section getSections(SectionType type) {
		return sections.get(type);
	}

	public Map<ContactType, String> getContacts() {
		return contacts;
	}

	public Map<SectionType, Section> getSections() {
		return sections;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	//	@Override
	public int compareTo(Resume o) {
		return fullName.compareTo(o.fullName);
	}

	@Override
	public String toString() {
		return "Resume{" +
				"uuid='" + uuid + '\'' +
				", fullName='" + fullName + '\'' +
				", location='" + location + '\'' +
				", homePage='" + homePage + '\'' +
				", contacts=" + contacts +
				", sections=" + sections +
				'}';
	}
}
