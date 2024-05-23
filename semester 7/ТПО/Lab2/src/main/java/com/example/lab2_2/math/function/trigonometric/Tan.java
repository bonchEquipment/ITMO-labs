package com.example.lab2_2.math.function.trigonometric;


import com.example.lab2_2.math.MathFunction;

public class Tan extends MathFunction {

    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Tan(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double cosValue = cos.calculate(x);
        if (cosValue == 0) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        double sinValue = sin.calculate(x);
        return sinValue / cosValue;
    }
}