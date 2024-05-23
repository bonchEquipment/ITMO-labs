package com.example.lab2_2.math.function.trigonometric;


import com.example.lab2_2.math.MathFunction;

public class Cos extends MathFunction {

    private final Sin sin;

    public Cos() {
        this.sin = new Sin();
    }

    public Cos(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double angle = Math.PI / 2 - x;
        return sin.calculate(angle);
    }
}