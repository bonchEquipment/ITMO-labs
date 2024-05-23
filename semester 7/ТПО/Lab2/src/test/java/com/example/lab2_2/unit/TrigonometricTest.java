package com.example.lab2_2.unit;

import com.example.lab2_2.math.function.trigonometric.Cos;
import com.example.lab2_2.math.function.trigonometric.Cot;
import com.example.lab2_2.math.function.trigonometric.Csc;
import com.example.lab2_2.math.function.trigonometric.Sec;
import com.example.lab2_2.math.function.trigonometric.Sin;
import com.example.lab2_2.math.function.trigonometric.Tan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrigonometricTest {

    static final double EPSILON = 1e-4;
    Sin sin;
    Cos cos;
    Tan tan;
    Cot cot;
    Csc csc;
    Sec sec;

    @BeforeAll
    void init() {
        sin = new Sin();
        cos = new Cos();
        tan = new Tan();
        cot = new Cot();
        csc = new Csc();
        sec = new Sec();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, -0.75, 0.0, Math.PI / 2, 1.2, Math.PI})
    void testSinCalculation(double x) {
        assertEquals(Math.sin(x), sin.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidSinCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> sin.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, -0.75, 0.0, Math.PI / 2, 1.2, Math.PI})
    void testCosCalculation(double x) {
        assertEquals(Math.cos(x), cos.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCosCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> cos.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, -0.45, Math.PI / 2, 0.54})
    void testCscCalculation(double x) {
        assertEquals(1 / Math.sin(x), csc.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, 0.0, Math.PI, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCscCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> csc.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -0.45, 0.0, 0.54, Math.PI})
    void testSecCalculation(double x) {
        assertEquals(1 / Math.cos(x), sec.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidSecCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> sec.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, 0.0, 1.2})
    void testTanCalculation(double x) {
        assertEquals(Math.tan(x), tan.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidTanCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> tan.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, Math.PI / 2, 1.2})
    void testCotCalculation(double x) {
        assertEquals(1 / Math.tan(x), cot.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCotCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> cot.calculate(x));
    }
}