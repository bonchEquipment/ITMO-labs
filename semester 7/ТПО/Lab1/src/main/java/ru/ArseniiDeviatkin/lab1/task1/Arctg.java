package ru.ArseniiDeviatkin.lab1.task1;

public class Arctg {

    private static final double EPSILON = 0.1;
    private static final double TWO_PI = 2 * Math.PI;

    public static double calculate(double x) {
        return calculate(x, EPSILON);
    }


    public static double calculate(double x, double epsilon) {
        if (!Double.isFinite(x)) {
            return Double.NaN;
        }

        while (x > TWO_PI ) {
            x -= TWO_PI;
        }
        while (x < -1 * TWO_PI ) {
            x += TWO_PI;
        }
        double result = 0.0, term = x;
        int n = 0;
        while (Math.abs(term) > epsilon) {
            term = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / (2 * n + 1);
            result += term;
            n++;
        }
        return result;
    }
}
