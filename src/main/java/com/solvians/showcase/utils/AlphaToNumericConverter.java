package com.solvians.showcase.utils;

import java.util.Map;


public final class AlphaToNumericConverter {

    private static final Map<Character, Integer> MAP = Map.ofEntries(
            Map.entry('A', 10), Map.entry('B', 11), Map.entry('C', 12), Map.entry('D', 13), Map.entry('E', 14),
            Map.entry('F', 15), Map.entry('G', 16), Map.entry('H', 17), Map.entry('I', 18), Map.entry('J', 19),
            Map.entry('K', 20), Map.entry('L', 21), Map.entry('M', 22), Map.entry('N', 23), Map.entry('O', 24),
            Map.entry('P', 25), Map.entry('Q', 26), Map.entry('R', 27), Map.entry('S', 28), Map.entry('T', 29),
            Map.entry('U', 30), Map.entry('V', 31), Map.entry('W', 32), Map.entry('X', 33), Map.entry('Y', 34),
            Map.entry('Z', 35)
    );

    private AlphaToNumericConverter() {
    }

    public static String convert(char c) {
        if (Character.isLetter(c)) {
            Integer val = MAP.get(Character.toUpperCase(c));
            if (val == null) throw new IllegalArgumentException("Unsupported letter: " + c);
            return Integer.toString(val);
        }
        if (Character.isDigit(c)) {
            return Character.toString(c);
        }
        throw new IllegalArgumentException("Unsupported character: " + c);
    }

}
