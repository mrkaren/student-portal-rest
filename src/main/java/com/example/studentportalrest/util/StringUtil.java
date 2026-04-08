package com.example.studentportalrest.util;

public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
