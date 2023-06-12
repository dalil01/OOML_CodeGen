package com.ooml_codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoggerTest {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.info(exception);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = GREEN + new Date().toString() + " INFO " + exception.toString() + RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void warnTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger logger = new Logger();
        logger.warn(exception);

        // Get the output from the ByteArrayOutputStream
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = new Date().toString() + " WARN " + exception.toString();

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
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = new Date().toString() + " ERROR " + exception.toString();

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

}
