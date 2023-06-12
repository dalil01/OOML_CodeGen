package com.ooml_codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoggerTest {

    @Test
    public void traceTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.trace(exception);

        String output = outputStream.toString().trim();
        String expectedOutput = new Date() + " TRACE " + exception;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void debugTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.debug(exception);

        String output = outputStream.toString().trim();
        String expectedOutput = new Date() + " DEBUG " + exception;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void infoTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.info(exception);

        String output = "\u001B" + outputStream.toString().trim();
        String expectedOutput = ColorCode.GREEN + new Date().toString() + " INFO " + exception + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void warnTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.warn(exception);

        String output = "\u001B" + outputStream.toString().trim();
        String expectedOutput = ColorCode.YELLOW + new Date().toString() + " WARN " + exception + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

    @Test
    public void errorTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Exception exception = new Exception();
        Logger.error(exception);

        String output = "\u001B" + outputStream.toString().trim();
        String expectedOutput = ColorCode.RED + new Date().toString() + " ERROR " + exception + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
        System.setOut(System.out);
    }

}
