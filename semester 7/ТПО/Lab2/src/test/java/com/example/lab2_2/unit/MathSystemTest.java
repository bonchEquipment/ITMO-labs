package com.example.lab2_2.unit;

import com.example.lab2_2.math.MathSystem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathSystemTest {

    static final double EPSILON = 1e-4;
    MathSystem mathSystem;

    @BeforeAll
    void init() {
        mathSystem = new MathSystem();
    }

    @ParameterizedTest
    @CsvSource({
            "7.678859148094316, -12.15",
            "7.14068447771217, -11.999",
            "16.644864444015212, -11.5",
            "-0.996639931949572, -3.84",
            "0.11929536004491087, -2.727",
            "0.0045, -2.479",
            "-0.4566150912034916, -2.3",
            "-6.55852306, -2",
            "4.693495848382859, -0.95",
            "0.0044, -0.66666",
            "0.08197662946305925, 0.5",
            "1215.6381886679444, 10",
    })
    void testMathSystemCalculation(double expected, double x) {
        assertEquals(expected, mathSystem.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCosCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> mathSystem.calculate(x));
    }
}
