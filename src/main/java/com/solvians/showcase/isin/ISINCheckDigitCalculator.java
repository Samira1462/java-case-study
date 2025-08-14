package com.solvians.showcase.isin;

import com.solvians.showcase.utils.IdentifierValidator;
import com.solvians.showcase.utils.LetterToDigitConverter;

import java.util.ArrayList;
import java.util.List;

public class ISINCheckDigitCalculator implements CheckDigitCalculator {

    @Override
    public int calculate(String partialIsin) {
        IdentifierValidator.validate(partialIsin);

        var digits = expandToDigits(partialIsin);
        var transformed = doubleEveryOtherDigitFromLeft(digits);
        var sum = sumDigits(transformed);

        return calculateCheckDigit(sum);
    }

    private List<Integer> expandToDigits(String partialIsin) {
        var expanded = new StringBuilder();
        for (char c : partialIsin.toCharArray()) {
            expanded.append(LetterToDigitConverter.convert(c));
        }

        List<Integer> digits = new ArrayList<>(expanded.length());
        for (char ch : expanded.toString().toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new IllegalArgumentException("Expanded ISIN contains non-digit: " + ch);
            }
            digits.add(ch - '0');
        }
        return digits;
    }

    private List<Integer> doubleEveryOtherDigitFromLeft(List<Integer> digits) {
        List<Integer> transformed = new ArrayList<>(digits.size());
        for (int i = 0; i < digits.size(); i++) {
            int val = digits.get(i);
            if (i % 2 == 0) {
                val *= 2;
            }
            transformed.add(val);
        }
        return transformed;
    }

    private int sumDigits(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += (num < 10) ? num : (num / 10) + (num % 10);
        }
        return sum;
    }

    private int calculateCheckDigit(int sum) {
        int modulo = sum % 10;
        return (modulo == 0) ? 0 : (10 - modulo);
    }
}
