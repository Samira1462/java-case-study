package com.solvians.showcase.isin;

public class ISINCreator {
    private final CheckDigitCalculator calculator;
    private final ISINBodyGenerator bodyGenerator;

    public ISINCreator(CheckDigitCalculator calculator, ISINBodyGenerator bodyGenerator) {
        this.calculator = calculator;
        this.bodyGenerator = bodyGenerator;
    }

    public String generate() {
        String body = bodyGenerator.generateBody();
        int check = calculator.calculate(body);
        return body + check;
    }
}