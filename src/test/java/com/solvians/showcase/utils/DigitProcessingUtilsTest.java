package com.solvians.showcase.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DigitProcessingUtilsTest {

    @Test
    void doubleEveryOtherDigitFromLeft() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> expected = List.of(2, 2, 6, 4, 10);
        List<Integer> actual = DigitProcessingUtils.doubleEveryOtherDigitFromLeft(input);
        assertEquals(expected, actual);
    }

    @Test
    void sumDigitsCorrectly() {
        List<Integer> input = List.of(2, 2, 6, 4, 10);
        int expected = 2 + 2 + 6 + 4 + 1;
        int actual = DigitProcessingUtils.sumDigits(input);
        assertEquals(expected, actual);
    }
}