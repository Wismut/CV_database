package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OrganizationSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private List<Organization> values;

    public OrganizationSection(Organization... organizations) {
        this.values = new LinkedList<>(Arrays.asList(organizations));
    }
}
