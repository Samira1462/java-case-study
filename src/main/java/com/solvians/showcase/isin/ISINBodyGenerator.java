package com.solvians.showcase.isin;

import java.util.concurrent.ThreadLocalRandom;

public class ISINBodyGenerator {
    private static final int LETTER_COUNT = 2;
    private static final int ALPHANUMERIC_COUNT = 9;

    public String generateBody() {
        StringBuilder sb = new StringBuilder(LETTER_COUNT + ALPHANUMERIC_COUNT);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < LETTER_COUNT; i++) {
            var c = (char) ('A' + random.nextInt(26));
            sb.append(c);
        }

        for (int i = 0; i < ALPHANUMERIC_COUNT; i++) {
            if (random.nextBoolean()) {
                var c = (char) ('A' + random.nextInt(26));
                sb.append(c);
            } else {
                var d = (char) ('0' + random.nextInt(10));
                sb.append(d);
            }
        }

        return sb.toString();
    }
}