package com.example.studentportalrest.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void isNullOrEmpty_null_true() {
        String str = null;
        boolean result = StringUtil.isNullOrEmpty(str);
        assertTrue(result);
    }

   @Test
    void isNullOrEmpty_empty_true() {
        String str = "";
        boolean result = StringUtil.isNullOrEmpty(str);
        assertTrue(result);
    }

    @Test
    void trim_OnlySpaces() {
        String str = "   ";
        String result = StringUtil.trim(str);
        assertEquals("", result);
    }

    @Test
    void trim_SpacesAndLetters() {
        String str = "   Hello World   ";
        String result = StringUtil.trim(str);
        assertEquals("Hello World", result);
    }

    @Test
    void trim_Null() {
        String str = null;
        String result = StringUtil.trim(str);
        assertNull(result);
    }


    @Test
    void capitalize() {
        String str = "hello";
        String result = StringUtil.capitalize(str);
        assertEquals("Hello", result);
    }

    @Test
    void capitalize_null() {
        String str = null;
        String result = StringUtil.capitalize(str);
        assertNull(result);
    }
}
