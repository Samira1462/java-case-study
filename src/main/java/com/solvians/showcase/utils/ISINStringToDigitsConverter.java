package com.solvians.showcase.utils;

import java.util.List;
import java.util.stream.Collectors;

public final class ISINStringToDigitsConverter {

    private ISINStringToDigitsConverter() {
    }

    public static List<Integer> convertToDigits(String partialIsin) {

        return partialIsin.chars()
                .mapToObj(c -> AlphaToNumericConverter.convert((char) c))
                .flatMap(s -> s.chars().mapToObj(d -> d - '0'))
                .collect(Collectors.toList());
    }
}
