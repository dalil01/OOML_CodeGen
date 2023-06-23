package com.ooml_codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ooml_codegen.utils.enums.ColorCode;

public class ULoggerTest {

    private final String error = "Error";

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