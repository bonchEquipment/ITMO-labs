package com.example.lab2_2.math.function.trigonometric;


import com.example.lab2_2.math.MathFunction;

public class Sec extends MathFunction {

    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    public Sec(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double cosValue = cos.calculate(x);
        if (cosValue == 0) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return 1 / cosValue;
    }
}
