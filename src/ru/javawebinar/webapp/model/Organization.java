package ru.javawebinar.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Organization implements Serializable {
	static final long serialVersionUID = 1L;

	private Link link;
	private List<Period> periods;

	public Organization(String organization12, String s) {
	}

	public Organization(Link link, List<Period> periods) {
		this.link = link;
		this.periods = periods;
	}

	public static class Period implements Serializable {
		static final long serialVersionUID = 1L;

		public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

		private LocalDate startDate;
		private LocalDate endDate;
		private String position;
		private String content;

		public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
			this.startDate = startDate;
			this.endDate = endDate;
			this.position = position;
			this.content = content;
		}

		public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
			this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), position, content);
		}

		public Period() {
		}
	}
}
