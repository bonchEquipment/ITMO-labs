package com.example.lab2_2.integration;

import com.example.lab2_2.math.function.logariphmic.Ln;
import com.example.lab2_2.math.function.logariphmic.Log;
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
public class LogarithmicTest {

    static final double EPSILON = 1e-4;
    @Mock
    Ln lnMock;
    @Spy
    Ln lnSpy;

    @Test
    void testLog2CalculationLnFunctionCalled() {
        Log log2 = new Log(2);
        setField(log2, "ln", lnSpy);
        log2.calculate(8);
        verify(lnSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testLog2CalculationWithMockedLnValues() {
        Log log2 = new Log(2);
        setField(log2, "ln", lnMock);
        when(lnMock.calculate(2)).thenReturn(0.69314);
        when(lnMock.calculate(8)).thenReturn(2.07944);
        assertEquals(3, log2.calculate(8), EPSILON);
    }
}