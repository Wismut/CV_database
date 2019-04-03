package ru.javawebinar.webapp.util;

public class Util {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
