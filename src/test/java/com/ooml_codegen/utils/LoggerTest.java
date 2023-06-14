package com.ooml_codegen.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.ooml_codegen.utils.enums.ColorCode;

public class LoggerTest {

    private final String error = "Error";

    @Test
    public void traceTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // System.out needs to be stored before changing the System stream
        // as System.out refers to the current output stream and not the default one.
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        Logger.trace(error);

        String output = outputStream.toString().trim();
        System.setOut(out);

        String expectedOutput = new Date() + " TRACE " + error;

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void debugTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        Logger.debug(error);

        String output = outputStream.toString().trim();
        System.setOut(out);

        String expectedOutput = new Date() + " DEBUG " + error;

        Assertions.assertEquals(expectedOutput, output);

    }

    @Test
    public void infoTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        Logger.info(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedOutput = ColorCode.GREEN + new Date().toString() + " INFO " + error + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void warnTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        Logger.warn(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedOutput = ColorCode.YELLOW + new Date().toString() + " WARN " + error + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void errorTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(outputStream));

        Logger.error(error);

        String output = "\u001B" + outputStream.toString().trim();
        System.setOut(out);

        String expectedOutput = ColorCode.RED + new Date().toString() + " ERROR " + error + ColorCode.RESET;

        Assertions.assertEquals(expectedOutput, output);
    }

}
