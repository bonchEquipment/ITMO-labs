package com.example.lab2_2.math.function.trigonometric;


import com.example.lab2_2.math.MathFunction;

public class Csc extends MathFunction {

    private final Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    public Csc(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double sinValue = sin.calculate(x);
        if (sinValue == 0) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return 1 / sinValue;
    }
}