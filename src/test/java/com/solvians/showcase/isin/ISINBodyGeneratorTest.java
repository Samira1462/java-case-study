package com.solvians.showcase.isin;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ISINBodyGeneratorTest {

    private final ISINBodyGenerator generator = new ISINBodyGenerator();

    @Test
    void generatedBodyHasCorrectLength() {
        String body = generator.generateBody();
        assertEquals(11, body.length(), "ISIN body should have length 11");
    }

    @Test
    void generatedBodyStartsWithTwoLetters() {
        String body = generator.generateBody();
        String firstTwo = body.substring(0, 2);
        assertTrue(firstTwo.chars().allMatch(Character::isUpperCase),
                "First two characters should be uppercase letters");
    }

    @Test
    void generatedBodyHasValidAlphanumericCharacters() {
        String body = generator.generateBody();
        String remaining = body.substring(2);
        Pattern pattern = Pattern.compile("[A-Z0-9]{9}");
        assertTrue(pattern.matcher(remaining).matches(),
                "Last 9 characters should be uppercase letters or digits");
    }
}