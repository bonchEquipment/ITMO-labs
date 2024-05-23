package com.example.lab2_2.integration;

import com.example.lab2_2.math.function.trigonometric.Cos;
import com.example.lab2_2.math.function.trigonometric.Cot;
import com.example.lab2_2.math.function.trigonometric.Csc;
import com.example.lab2_2.math.function.trigonometric.Sec;
import com.example.lab2_2.math.function.trigonometric.Sin;
import com.example.lab2_2.math.function.trigonometric.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;


@ExtendWith(MockitoExtension.class)
public class TrigonometricTest {

    static final double EPSILON = 1e-4;
    @Mock
    Sin sinMock;
    @Spy
    Sin sinSpy;
    @Mock
    Cos cosMock;
    @Spy
    Cos cosSpy;

    @Test
    void testCosCalculationSinFunctionCalled() {
        Cos cos = new Cos();
        setField(cos, "sin", sinSpy);
        cos.calculate(1);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testCosCalculationWithMockedSinValues() {
        Cos cos = new Cos();
        setField(cos, "sin", sinMock);
        when(sinMock.calculate(Math.PI / 2)).thenReturn(1.0);
        assertEquals(1.0, cos.calculate(0), EPSILON);
    }

    @Test
    void testCscCalculationSinFunctionCalled() {
        Csc csc = new Csc();
        setField(csc, "sin", sinSpy);
        csc.calculate(1);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testCscCalculationWithMockedSinValues() {
        Csc csc = new Csc();
        setField(csc, "sin", sinMock);
        double x = Math.PI / 4;
        when(sinMock.calculate(x)).thenReturn(1 / Math.sqrt(2));
        assertEquals(Math.sqrt(2), csc.calculate(x), EPSILON);
    }

    @Test
    void testSecCalculationCosFunctionCalled() {
        Sec sec = new Sec();
        setField(sec, "cos", cosSpy);
        sec.calculate(1);
        verify(cosSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testSecCalculationWithMockedCosValues() {
        Sec sec = new Sec();
        setField(sec, "cos", cosMock);
        double x = 0;
        when(cosMock.calculate(x)).thenReturn(1.0);
        assertEquals(1.0, sec.calculate(x), EPSILON);
    }

    @Test
    void testTanCalculationSinAndCosFunctionsCalled() {
        Tan tan = new Tan();
        setField(tan, "sin", sinSpy);
        setField(tan, "cos", cosSpy);
        tan.calculate(1);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
        verify(cosSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testTanCalculationWithMockedSinAndCosValues() {
        Tan tan = new Tan();
        setField(tan, "sin", sinMock);
        setField(tan, "cos", cosMock);
        double x = 5;
        when(sinMock.calculate(x)).thenReturn(-0.95892427);
        when(cosMock.calculate(x)).thenReturn(0.28366218);
        assertEquals(-3.3805, tan.calculate(x), EPSILON);
    }



    @Test
    void testCotCalculationCosAndSinFunctionsCalled() {
        Cot cot = new Cot();
        setField(cot, "sin", sinSpy);
        setField(cot, "cos", cosSpy);
        cot.calculate(1);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
        verify(cosSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testCotCalculationWithMockedCosAndSinValues() {
        Cot cot = new Cot();
        setField(cot, "cos", cosMock);
        setField(cot, "sin", sinMock);
        double x = 5;
        when(cosMock.calculate(x)).thenReturn(0.28366218);
        when(sinMock.calculate(x)).thenReturn(-0.95892427);
        assertEquals(-0.2958, cot.calculate(x), EPSILON);
    }
}