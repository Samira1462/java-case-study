package com.solvians.showcase.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierValidatorTest {

    @ParameterizedTest(name = "invalid identifier: {0}")
    @ValueSource(strings = { "", "TOO SHORT", "1E123456789", "1S123456789" })
    void givenInvalidIdentifierThenThrowsIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class, () -> IdentifierValidator.validate(input));
    }

    @Test
    void givenValidIdentifierThenDoesNotThrow() {
        assertDoesNotThrow(() -> IdentifierValidator.validate("DE123456789"));
    }

    @Test
    void givenNullIdentifierThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> IdentifierValidator.validate(null));
    }
}