package com.solvians.showcase.utils;

public final class IdentifierValidator {
    private IdentifierValidator() {
    }

    public static void validate(String partialIsin) {

        if (partialIsin == null) {
            throw new IllegalArgumentException("partialIsin is null");
        }
        if (partialIsin.length() != 11) {
            throw new IllegalArgumentException(
                    "partialIsin must be exactly 11 characters, got: " + partialIsin.length());
        }

        if (!Character.isLetter(partialIsin.charAt(0)) || !Character.isLetter(partialIsin.charAt(1))) {
            throw new IllegalArgumentException("ISIN must start with two letters");
        }
    }
}
