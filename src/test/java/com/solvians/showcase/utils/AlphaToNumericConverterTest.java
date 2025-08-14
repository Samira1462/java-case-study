package com.solvians.showcase.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphaToNumericConverterTest {

    @Test
    void convertsUpperCaseLetter() {
        assertEquals("10", AlphaToNumericConverter.convert('A'));
        assertEquals("35", AlphaToNumericConverter.convert('Z'));
    }

    @Test
    void convertsLowerCaseLetter() {
        assertEquals("10", AlphaToNumericConverter.convert('a'));
        assertEquals("35", AlphaToNumericConverter.convert('z'));
    }

    @Test
    void throwsExceptionForUnsupportedLetter() {
        assertThrows(IllegalArgumentException.class, () -> AlphaToNumericConverter.convert('#'));
    }

    @Test
    void returnsDigitAsString() {
        assertEquals("0", AlphaToNumericConverter.convert('0'));
        assertEquals("9", AlphaToNumericConverter.convert('9'));
    }

}