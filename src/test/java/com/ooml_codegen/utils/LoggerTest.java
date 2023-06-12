package com.ooml_codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoggerTest {
    @Test
    public void traceTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger logger = new Logger();
        logger.trace(exception);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = new Date().toString() + " TRACE " + exception.toString();

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void debugTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger logger = new Logger();
        logger.debug(exception);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = new Date().toString() + " DEBUG " + exception.toString();

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void infoTest() {
        StringBuilder stringBuilder = new StringBuilder();
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                stringBuilder.append((char) b);
            }
        });
        System.setOut(printStream);

        Exception exception = new Exception();
        Logger.info(exception);

        // Get the output from the StringBuilder
        String output = "\u001B" + stringBuilder.toString().trim();

        // Expected output
        String expectedOutput = ColorCode.GREEN + new Date().toString() + " INFO " + exception.toString() + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }



    @Test
    public void warnTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.warn(exception);

        // Get the output from the ByteArrayOutputStream
        String output = "\u001B" + outputStream.toString().trim();

        // Expected output
        String expectedOutput = ColorCode.YELLOW + new Date().toString() + " WARN " + exception.toString() + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void errorTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger logger = new Logger();
        logger.error(exception);

        // Get the output from the ByteArrayOutputStream
        String output = "\u001B" + outputStream.toString().trim();

        // Expected output
        String expectedOutput = ColorCode.RED + new Date().toString() + " ERROR " + exception.toString() + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

}
