package com.solvians.showcase.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AlphaToNumericConverterTest {

    @ParameterizedTest(name = "convert({0}) = {1}")
    @CsvSource({
            "A,10",
            "Z,35",
            "a,10",
            "z,35",
            "0,0",
            "9,9"
    })
    void convertsLettersAndDigits(char input, String expected) {
        assertEquals(expected, AlphaToNumericConverter.convert(input));
    }

    @ParameterizedTest(name = "convert({0}) throws IllegalArgumentException")
    @CsvSource({
            "#",
            "@",
            "!"
    })
    void throwsExceptionForUnsupportedChars(char input) {
        assertThrows(IllegalArgumentException.class, () -> AlphaToNumericConverter.convert(input));
    }

}