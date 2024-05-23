package com.example.lab2_2.integration;

import com.example.lab2_2.math.MathSystem;
import com.example.lab2_2.math.function.logariphmic.Ln;
import com.example.lab2_2.math.function.logariphmic.Log;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;


@ExtendWith(MockitoExtension.class)
public class MathSystemTest {

    static final double EPSILON = 1e-4;
    @Mock(lenient = true)
    Sin sinMock;
    @Mock(lenient = true)
    Cos cosMock;
    @Mock
    Csc cscMock;
    @Mock(lenient = true)
    Sec secMock;
    @Mock(lenient = true)
    Tan tanMock;
    @Mock
    Cot cotMock;
    @Mock
    Ln lnMock;
    @Mock
    Log log2Mock;
    @Mock
    Log log3Mock;
    @Mock
    Log log5Mock;
    @Spy
    Sin sinSpy;
    @Spy
    Cos cosSpy;
    @Spy
    Csc cscSpy;
    @Spy
    Sec secSpy;
    @Spy
    Tan tanSpy;
    @Spy
    Cot cotSpy;
    @Spy
    Ln lnSpy;
    Log log2Spy = spy(new Log(2));
    Log log3Spy = spy(new Log(3));
    Log log5Spy = spy(new Log(5));

    @Test
    void testMathSystemCalculationTrigonometricFunctionsCalled() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sin", sinSpy);
        setField(mathSystem, "cos", cosSpy);
        setField(mathSystem, "csc", cscSpy);
        setField(mathSystem, "sec", secSpy);
        setField(mathSystem, "tan", tanSpy);
        setField(mathSystem, "cot", cotSpy);
        mathSystem.calculate(-2);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
        verify(cosSpy, atLeastOnce()).calculate(anyDouble());
        verify(secSpy, atLeastOnce()).calculate(anyDouble());
        verify(tanSpy, atLeastOnce()).calculate(anyDouble());
        verify(cotSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedTrigonometricFunctionValues() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sin", sinMock);
        setField(mathSystem, "cos", cosMock);
        setField(mathSystem, "sec", secMock);
        setField(mathSystem, "tan", tanMock);
        double x = 2;
        when(sinMock.calculate(x)).thenReturn(0.909297);
        when(cosMock.calculate(x)).thenReturn(-0.41614);
        when(secMock.calculate(x)).thenReturn(-2.40299);
        when(tanMock.calculate(x)).thenReturn(-2.1850);
        assertEquals(0.08197189891510903, mathSystem.calculate(x), EPSILON);
    }



}
