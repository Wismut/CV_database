package ru.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private List<Organization> values;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... organizations) {
        this.values = new LinkedList<>(Arrays.asList(organizations));
    }

    public <T> OrganizationSection(List<T> readList) {
        values = (List<Organization>) readList;
    }

    public List<Organization> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
