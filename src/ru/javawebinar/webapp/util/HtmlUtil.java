package ru.javawebinar.webapp.util;

import ru.javawebinar.webapp.model.ContactType;
import ru.javawebinar.webapp.model.Organization;
import ru.javawebinar.webapp.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {
    public static final String EMPTY_TD = "<img src='img/s.gif'>";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContact(type);
        return contact == null ? EMPTY_TD : type.toHtml(contact);
    }

    public static String format(LocalDate date) {
        return date.equals(Organization.Period.NOW) ? "Now" : date.format(DATE_FORMATTER);
    }

}
