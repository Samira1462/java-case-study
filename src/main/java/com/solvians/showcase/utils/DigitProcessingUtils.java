package com.solvians.showcase.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public final class DigitProcessingUtils {

    private DigitProcessingUtils() {
    }

    public static List<Integer> doubleEveryOtherDigitFromLeft(List<Integer> digits) {
        return IntStream.range(0, digits.size())
                .mapToObj(i -> (i % 2 == 0) ? digits.get(i) * 2 : digits.get(i))
                .collect(Collectors.toList());
    }

    public static int sumDigits(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(n -> (n < 10) ? n : (n / 10) + (n % 10))
                .sum();
    }
}
