package com.solvians.showcase.isin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ISINCheckDigitCalculatorTest {
    private final ISINCheckDigitCalculator calculator = new ISINCheckDigitCalculator();


    @ParameterizedTest
    @CsvSource({
            "DE123456789, 6",
            "US037833100, 5"
    })
    void givenISINWhenCalculateCheckDigitThenReturnsCorrectCheckDigit(String isin, int expected) {
        int actual = calculator.calculate(isin);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1E123456789",
            "D1123456789",
            "12345678901"
    })
    void givenISINStartingWithInvalidChars_thenThrows(String isin) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(isin));
        assertTrue(ex.getMessage().contains("two letters"));
    }

    @Test
    public void givenISINWithInvalidLengthThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("SHORT"));
    }

    @Test
    void givenNullInputWhenCalculateCheckDigitThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(null));
    }
}