package ru.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class MultiTextSection extends Section implements Serializable {
	static final long serialVersionUID = 1L;

	private List<String> values;

    public MultiTextSection(String[] values) {
        this.values = Arrays.asList(values);
    }

    public MultiTextSection(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
