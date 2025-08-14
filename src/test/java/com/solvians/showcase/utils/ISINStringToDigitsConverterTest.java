package com.solvians.showcase.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ISINStringToDigitsConverterTest {

    @Test
    void convertLettersToDigits() {
        List<Integer> digits = ISINStringToDigitsConverter.convertToDigits("DE123");
        // D=13, E=14 => "1314123"
        assertEquals(List.of(1,3,1,4,1,2,3), digits);
    }
}