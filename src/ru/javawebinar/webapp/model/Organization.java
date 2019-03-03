package ru.javawebinar.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
	static final long serialVersionUID = 1L;

	private Link link = Link.EMPTY;
	private List<Period> periods = new LinkedList<>();

	public Organization() {
	}

	public Organization(Link link, List<Period> periods) {
		this.link = link;
		this.periods = periods;
	}

	public Organization(String name, String url, Period... periods) {
		this(new Link(name, url), new LinkedList<>(Arrays.asList(periods)));
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public List<Period> getPeriods() {
		return periods;
	}

	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Period implements Serializable {
		static final long serialVersionUID = 1L;

		public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

		private LocalDate startDate = NOW;
		private LocalDate endDate;
		private String position;
		private String content = "";

		public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
			Objects.requireNonNull(startDate, "startDate is null");
			Objects.requireNonNull(position, "position is null");
			this.startDate = startDate;
			this.endDate = endDate == null ? NOW : endDate;
			this.position = position;
			this.content = content == null ? "" : content;
		}

		public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
			this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
		}

		public Period() {
		}

		public Period(LocalDate startDate, LocalDate endDate) {
			this(startDate, endDate, null, null);
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}
}
