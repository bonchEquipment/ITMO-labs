package com.example.lab2_2.math.function.trigonometric;


import com.example.lab2_2.math.MathFunction;

public class Sin extends MathFunction {

    public Sin() {
    }

    public Sin(double precision, int maxIterations) {
        super(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        if (!Double.isFinite(x)) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        double result = x, term = x;
        int n = 1;
        while (Math.abs(term) > precision) {
            term *= -x * x / ((2 * n) * (2 * n + 1));
            result += term;
            n++;
        }
        return Math.abs(result) < precision ? 0 : result;
    }
}
