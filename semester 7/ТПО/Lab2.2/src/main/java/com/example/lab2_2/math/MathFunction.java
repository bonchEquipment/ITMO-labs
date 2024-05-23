package com.example.lab2_2.math;

public abstract class MathFunction {

    public static final double DEFAULT_PRECISION = 1e-10;
    public static final int DEFAULT_MAX_ITERATIONS = 100000;
    protected final double precision;
    protected final int maxIterations;

    public MathFunction() {
        this.precision = DEFAULT_PRECISION;
        this.maxIterations = DEFAULT_MAX_ITERATIONS;
    }

    public MathFunction(double precision, int maxIterations) {
        if (!Double.isFinite(precision) || precision <= 0 || precision >= 1) {
            throw new IllegalArgumentException("Precision must be less than one and greater than zero.");
        }
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("Max iterations must be greater than zero.");
        }
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    public abstract double calculate(double x);
}
