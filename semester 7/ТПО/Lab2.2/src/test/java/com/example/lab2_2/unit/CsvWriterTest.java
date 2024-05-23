package com.example.lab2_2.unit;

import com.example.lab2_2.math.MathFunction;
import com.example.lab2_2.writer.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CsvWriterTest {

    @TempDir
    File csvDirectory;

    @Test
    void testWritIncrementFunctionIntoFile() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvWriter writer = new CsvWriter(file, ',');
        writer.write(new IncrementFunction(), 0, 3, 1);
        assertEquals(
                Arrays.asList(
                        "0.000000,1.000000",
                        "1.000000,2.000000",
                        "2.000000,3.000000",
                        "3.000000,4.000000"
                ),
                Files.readAllLines(file.toPath())
        );
    }

    @Test
    void testWritSquareFunctionIntoFile() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvWriter writer = new CsvWriter(file, ',');
        writer.write(new SquareFunction(), 0,  8, 2);
        assertEquals(
                Arrays.asList(
                        "0.000000,0.000000",
                        "2.000000,4.000000",
                        "4.000000,16.000000",
                        "6.000000,36.000000",
                        "8.000000,64.000000"
                ),
                Files.readAllLines(file.toPath())
        );
    }

    private static class IncrementFunction extends MathFunction {

        @Override
        public double calculate(double x) {
            return x + 1;
        }
    }

    private static class SquareFunction extends MathFunction {

        @Override
        public double calculate(double x) {
            return x * x;
        }
    }
}
