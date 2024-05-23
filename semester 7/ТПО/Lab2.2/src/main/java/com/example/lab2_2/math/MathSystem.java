package com.example.lab2_2.math;


import com.example.lab2_2.math.function.logariphmic.Ln;
import com.example.lab2_2.math.function.logariphmic.Log;
import com.example.lab2_2.math.function.trigonometric.Cos;
import com.example.lab2_2.math.function.trigonometric.Cot;
import com.example.lab2_2.math.function.trigonometric.Csc;
import com.example.lab2_2.math.function.trigonometric.Sec;
import com.example.lab2_2.math.function.trigonometric.Sin;
import com.example.lab2_2.math.function.trigonometric.Tan;

public class MathSystem extends MathFunction {

    private final Sin sin;
    private final Cos cos;
    private final Csc csc;
    private final Sec sec;
    private final Tan tan;
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public MathSystem() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.csc = new Csc();
        this.sec = new Sec();
        this.tan = new Tan();
        this.cot = new Cot();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
        this.log10 = new Log(10);
    }

    public MathSystem(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
        this.csc = new Csc(precision, maxIterations);
        this.sec = new Sec(precision, maxIterations);
        this.tan = new Tan(precision, maxIterations);
        this.cot = new Cot(precision, maxIterations);
        this.ln = new Ln(precision, maxIterations);
        this.log2 = new Log(2, precision, maxIterations);
        this.log3 = new Log(3, precision, maxIterations);
        this.log5 = new Log(5, precision, maxIterations);
        this.log10 = new Log(10, precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double result;
        if (x <= 0) {
            // x <= 0 : (((((tan(x) / tan(x)) ^ 3) * sec(x)) / ((cot(x) - csc(x)) + sin(x))) * (cos(x) + tan(x)))
            result = ((Math.pow(tan.calculate(x) / tan.calculate(x), 3) * sec.calculate(x)) / ((cot.calculate(x) - csc.calculate(x)) + sin.calculate(x)) * (cos.calculate(x) + tan.calculate(x)));
        } else {
            // x > 0 : (((((log_3(x) ^ 3) / log_5(x)) * log_10(x)) ^ 2) * ((log_2(x) + log_3(x)) ^ 2))
            result = ((Math.pow(((Math.pow(log3.calculate(x), 3) / log5.calculate(x)) * log10.calculate(x)), 2)) * (Math.pow((log2.calculate(x) + log3.calculate(x)), 2)));
        }
        if (!Double.isFinite(result)) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return result;
    }
}
