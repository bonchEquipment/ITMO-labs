package ru.ArseniiDeviatkin.lab1.task1;


import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArctgTest {

    private static final double EPSILON = 0.01;


    @ParameterizedTest
    @CsvSource({
            "-100000000000, -0.78539816339",
            "-0.577350269, -0.52359877559",
            "0, 0",
            "0.577350269, 0.52359877559",
            "1, 0.78539816339"
    })
    void testArctgWithinRange(double x, double expected) {
        assertEquals(expected, Arctg.calculate(x, EPSILON), EPSILON);
    }


    @ParameterizedTest
    @ValueSource(doubles = {
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void testArctgOutsideRange(double x) {
        assertTrue(Double.isNaN(Arctg.calculate(x)));
    }

    @RepeatedTest(100)
    void testArctgRandomValues() {
        int min = -1;
        int max = 1;
        double x = Math.random() * (max - min) + min;

        assertEquals(Math.atan(x), Arctg.calculate(x, EPSILON), EPSILON);
    }
}
