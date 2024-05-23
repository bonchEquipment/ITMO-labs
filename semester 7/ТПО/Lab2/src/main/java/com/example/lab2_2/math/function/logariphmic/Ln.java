package com.example.lab2_2.math.function.logariphmic;


import com.example.lab2_2.math.MathFunction;

public class Ln extends MathFunction {

    public Ln() {
    }

    public Ln(double precision, int maxIterations) {
        super(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        if (x <= 0 || !Double.isFinite(x)) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        boolean isAbsLessThanOne = Math.abs(x - 1) <= 1;
        double raw = 0.0, term = 1.0;
        int n = 1;
        while (Math.abs(term) >= precision && n <= maxIterations) {
            raw -= Math.pow(-1, n) * Math.pow(x - 1, isAbsLessThanOne ? n : -n) / n;
            n++;
        }
        return isAbsLessThanOne ? raw : raw + calculate(x - 1);
    }
}