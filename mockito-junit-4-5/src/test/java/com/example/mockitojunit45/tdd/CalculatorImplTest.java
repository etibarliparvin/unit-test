package com.example.mockitojunit45.tdd;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorImplTest {

    private Calculator calculator;

    @Test
    public void givenAAndBWhenAddThenC() {
        // arrange
        calculator = new CalculatorImpl();
        int a = 5;
        int b = 2;
        int c = 7;

        // act
        int result = calculator.add(a, b);

        // assert
        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenSubtractThenC() {
        // arrange
        calculator = new CalculatorImpl();
        int a = 7;
        int b = 2;
        int c = 5;

        // act
        int result = calculator.subtract(a, b);

        // assert
        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenMultiplyThenC() {
        // arrange
        calculator = new CalculatorImpl();
        int a = 7;
        int b = 2;
        int c = 14;

        // act
        int result = calculator.multiply(a, b);

        // assert
        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenDivideThenC() {
        // arrange
        calculator = new CalculatorImpl();
        int a = 14;
        int b = 2;
        int c = 7;

        // act
        int result = calculator.divide(a, b);

        // assert
        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenDivideByZeroThenException() {
        // arrange
        calculator = new CalculatorImpl();
        int a = 14;
        int b = 0;

        // act
        assertThatThrownBy(() -> calculator.divide(a, b))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");
    }
}