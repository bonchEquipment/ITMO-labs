package com.example.lab2_2.unit;

import com.example.lab2_2.ReflectionUtil;
import com.example.lab2_2.math.MathFunction;
import com.example.lab2_2.math.function.trigonometric.Cos;
import com.example.lab2_2.math.function.trigonometric.Cot;
import com.example.lab2_2.math.function.trigonometric.Csc;
import com.example.lab2_2.math.function.trigonometric.Sec;
import com.example.lab2_2.math.function.trigonometric.Sin;
import com.example.lab2_2.math.function.trigonometric.Tan;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathFunctionTest {

    List<Class<? extends MathFunction>> functions;

    @BeforeAll
    void init() {
        functions = Arrays.asList(
                Sin.class,
                Cos.class,
                Tan.class,
                Cot.class,
                Sec.class,
                Csc.class
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.1, -1.1, 1.0, 0.0, Double.NaN})
    void testInvalidPrecision(double precision) {
        functions.forEach(functionClass -> assertThrows(
                IllegalArgumentException.class,
                () -> ReflectionUtil.createInstance(
                        functionClass,
                        new Class[]{double.class, int.class},
                        precision, 1
                ))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void testInvalidMaxIterations(int maxIterations) {
        functions.forEach(functionClass -> assertThrows(
                IllegalArgumentException.class,
                () -> ReflectionUtil.createInstance(
                        functionClass,
                        new Class[]{double.class, int.class},
                        0.1, maxIterations
                ))
        );
    }
}
