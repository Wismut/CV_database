package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MultiTextSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private List<String> values;

    public MultiTextSection(String[] values) {
        this.values = Arrays.asList(values);
    }
}
