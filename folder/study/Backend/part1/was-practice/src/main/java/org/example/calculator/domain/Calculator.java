package org.example.calculator.domain;

import org.example.calculator.tobe.AdditionOperator;
import org.example.calculator.tobe.ArithmeticOperator;
import org.example.calculator.tobe.DivisionOperator;
import org.example.calculator.tobe.SubtractionOperator;
import org.example.calculator.tobe.MultiplicationOperator;

import java.util.List;

public class Calculator {

    private static final List<ArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new DivisionOperator(), new SubtractionOperator(), new MultiplicationOperator());
    /**
     * Perform simple arithmetic operations.
     * It can only be counted as a positive number.
     * Raise an IllegalArgument exception when dividing by zero in a division. Implement based on the MVC pattern (Model-View-Controller)
     */

    public static int calculate(PositiveNumber positiveOperand1, String operator, PositiveNumber positiveOperand2) {
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(positiveOperand1, positiveOperand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid arithmetic operation."));
    }
}