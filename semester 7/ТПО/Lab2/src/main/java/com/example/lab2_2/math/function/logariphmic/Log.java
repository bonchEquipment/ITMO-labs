package com.example.lab2_2.math.function.logariphmic;


import com.example.lab2_2.math.MathFunction;

public class Log extends MathFunction {

    private final Ln ln = new Ln();
    private final int base;

    public Log(int base) {
        checkBase(base);
        this.base = base;
    }

    public Log(int base, double precision, int maxIterations) {
        super(precision, maxIterations);
        checkBase(base);
        this.base = base;
    }

    @Override
    public double calculate(double x) {
        return ln.calculate(x) / ln.calculate(base);
    }

    private void checkBase(int base) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0.");
        }
    }
}
