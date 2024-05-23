package com.example.lab2_2.writer;

import com.example.lab2_2.math.MathFunction;
import java.io.IOException;


public interface IWriter {

    void write(
            MathFunction function,
            double from,
            double to,
            double step
    ) throws IOException;
}
