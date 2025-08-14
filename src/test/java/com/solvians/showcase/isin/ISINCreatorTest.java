package com.solvians.showcase.isin;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISINCreatorTest {

    private static ISINCheckDigitCalculator calculator;
    private static ISINBodyGenerator bodyGenerator;

    @BeforeAll
    static void setup() {
        calculator = new ISINCheckDigitCalculator();
        bodyGenerator = new ISINBodyGenerator();
    }

    @Test
    void givenISINCreator_whenGenerate_thenISINHasValidFormat() {
        ISINCreator creator = new ISINCreator(calculator, bodyGenerator);

        String isin = creator.generate();

        assertAll("Generated ISIN",
                () -> assertNotNull(isin, "ISIN should not be null"),
                () -> assertEquals(12, isin.length(), "ISIN should have 12 characters"),
                () -> assertTrue(isin.substring(0, 2).matches("[A-Z]{2}"), "First two characters should be letters"),
                () -> assertTrue(isin.substring(2, 11).matches("[A-Z0-9]{9}"), "Middle 9 characters should be alphanumeric"),
                () -> assertTrue(Character.isDigit(isin.charAt(11)), "Last character should be a digit (check digit)")
        );
    }
}