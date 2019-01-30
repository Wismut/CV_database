package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.List;

public class OrganizationSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private List<Organization> values;
}
