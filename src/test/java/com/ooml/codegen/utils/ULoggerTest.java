package com.ooml.codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.ooml.codegen.utils.ULogger.ColorCode;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ULoggerTest {

    private final String error = "Error";

    public static class ColorCodeTest {

        @Test
        void resetToStringTest() {
            Assertions.assertEquals("\033[0m", ULogger.ColorCode.RESET.toString());
        }

        @Test
        void greenStringTest() {
            Assertions.assertEquals("\u001B[32m", ULogger.ColorCode.GREEN.toString());
        }

        @Test
        void yellowStringTest() {
            Assertions.assertEquals("\u001B[33m", ULogger.ColorCode.YELLOW.toString());
        }

        @Test
        void redStringTest() {
            Assertions.assertEquals("\u001B[31m", ULogger.ColorCode.RED.toString());
        }

    }

    public static class ErrorTypeTest {

        @Test
        void resetToStringTest() {
            Assertions.assertEquals("TRACE", ULogger.ErrorType.TRACE.toString());
        }

        @Test
        void debugToStringTest() {
            Assertions.assertEquals("DEBUG", ULogger.ErrorType.DEBUG.toString());
        }

        @Test
        void infoToStringTest() {
            Assertions.assertEquals("INFO", ULogger.ErrorType.INFO.toString());
        }

        @Test
        void warnToStringTest() {
            Assertions.assertEquals("WARN", ULogger.ErrorType.WARN.toString());
        }

        @Test
        void errorToStringTest() {
            Assertions.assertEquals("ERROR", ULogger.ErrorType.ERROR.toString());
        }

    }

    private int checkTime(LocalDateTime before, String content){
        try {
            int separator = content.indexOf(' ');
            String toBeParsed = content.substring(0, separator);

            LocalDateTime tested = LocalDateTime.parse(toBeParsed);
            LocalDateTime after = LocalDateTime.now();

            // on the off chance that our tests runs really really fast...
            Assertions.assertTrue(before.isBefore(tested) || before.isEqual(tested));
            Assertions.assertTrue(tested.isBefore(after) || tested.isEqual(after));
            return separator;
        }
        catch (DateTimeParseException e){
            Assertions.fail(e);
            return 0;
        }
    }

    @Test
    public void debugTest() {
        LocalDateTime before = LocalDateTime.now();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        ULogger.debug(error);

        String output = outputStream.toString().trim();
        System.setOut(out);

        int length = checkTime(before, output);
        output = output.substring(length);

        String expectedOutput = " DEBUG " + error;

        Assertions.assertEquals(expectedOutput, output);

    }



    @Test
    public void traceTest() {
        LocalDateTime before = LocalDateTime.now();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // System.out needs to be stored before changing the System stream
        // as System.out refers to the current output stream and not the default one.
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        ULogger.trace(error);

        String output = outputStream.toString().trim();
        System.setOut(out);

        int length = checkTime(before, output);
        output = output.substring(length);

        String expectedOutput = " TRACE " + error;

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void infoTest() {
        LocalDateTime before = LocalDateTime.now();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        ULogger.info(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedPrefix = ColorCode.GREEN.toString();
        String prefix = output.substring(0, ColorCode.GREEN.toString().length());

        Assertions.assertEquals(expectedPrefix, prefix);

        output = output.substring(ColorCode.GREEN.toString().length());
        int length = checkTime(before, output);

        String expectedOutput = " INFO " + error + ColorCode.RESET;
        output = output.substring(length);

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void warnTest() {
        LocalDateTime before = LocalDateTime.now();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        ULogger.warn(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedPrefix = ColorCode.YELLOW.toString();
        String prefix = output.substring(0, ColorCode.YELLOW.toString().length());
        Assertions.assertEquals(expectedPrefix, prefix);

        output = output.substring(ColorCode.YELLOW.toString().length());
        int length = checkTime(before, output);

        String expectedOutput = " WARN " + error + ColorCode.RESET;
        output = output.substring(length);

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void errorTest() {
        LocalDateTime before = LocalDateTime.now();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        ULogger.error(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedPrefix = ColorCode.RED.toString();
        String prefix = output.substring(0, ColorCode.RED.toString().length());
        Assertions.assertEquals(expectedPrefix, prefix);

        output = output.substring(ColorCode.RED.toString().length());
        int length = checkTime(before, output);

        String expectedOutput = " ERROR " + error + ColorCode.RESET;
        output = output.substring(length);

        Assertions.assertEquals(expectedOutput, output);
    }

}