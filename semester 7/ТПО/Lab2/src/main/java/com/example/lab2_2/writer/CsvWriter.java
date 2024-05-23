package com.example.lab2_2.writer;

import com.example.lab2_2.math.MathFunction;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;


public class CsvWriter implements IWriter {

    private final File file;
    private final char separator;

    public CsvWriter(File file, char separator) {
        this.file = file;
        this.separator = separator;
    }

    public CsvWriter(String filename, char separator) {
        this.file = new File(filename);
        this.separator = separator;
    }

    @Override
    public void write(
            MathFunction function,
            double from,
            double to,
            double step
    ) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(file);
        for (double current = from; current <= to; current += step) {
            try {
                double value = function.calculate(current);
                printWriter.println(String.format(Locale.ENGLISH, "%f%c%f", current, separator, value));
            } catch (ArithmeticException ignored) {
            }
        }
        printWriter.close();
    }
}
