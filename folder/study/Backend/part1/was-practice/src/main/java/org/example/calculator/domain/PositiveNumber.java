package org.example.calculator.domain;

public class PositiveNumber {
    public static final String ZERO_OR_NEGATIVE_NUMBER_EXCEPTION_MESSAGE = "You can't pass 0 or negative numbers.";
    private final int value;

    public PositiveNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (isZeroOrNegativeNumber(value)) {
            throw new IllegalArgumentException(ZERO_OR_NEGATIVE_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private boolean isZeroOrNegativeNumber(int value) {
        return value <= 0;
    }

    public int toInt() {
        return value;
    }
}