package com.solvians.showcase.isin;

import com.solvians.showcase.utils.IdentifierValidator;

import static com.solvians.showcase.utils.DigitProcessingUtils.doubleEveryOtherDigitFromLeft;
import static com.solvians.showcase.utils.DigitProcessingUtils.sumDigits;
import static com.solvians.showcase.utils.ISINStringToDigitsConverter.convertToDigits;

public class ISINCheckDigitCalculator implements CheckDigitCalculator {

    @Override
    public int calculate(String partialIsin) {
        IdentifierValidator.validate(partialIsin);

        var digits = convertToDigits(partialIsin);
        var transformed = doubleEveryOtherDigitFromLeft(digits);
        var sum = sumDigits(transformed);

        return calculateCheckDigit(sum);
    }

    private int calculateCheckDigit(int sum) {
        int modulo = sum % 10;
        return (modulo == 0) ? 0 : (10 - modulo);
    }
}
