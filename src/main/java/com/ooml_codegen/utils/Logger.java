package com.ooml_codegen.utils;

import java.util.Date;

import static com.ooml_codegen.utils.errorType.DEBUG;
import static com.ooml_codegen.utils.errorType.ERROR;
import static com.ooml_codegen.utils.errorType.INFO;
import static com.ooml_codegen.utils.errorType.TRACE;
import static com.ooml_codegen.utils.errorType.WARN;

enum errorType {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}

public class Logger {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";


    public static void trace(Object message) {
        printMessage(TRACE, message.toString());
    }

    public static void debug(Object message) {
        printMessage(DEBUG, message.toString());
    }

    public static void info(Object message) {
        printMessage(INFO, message.toString());
    }


    public static void warn(Object message) {
        printMessage(WARN, message.toString());
    }


    public static void error(Object message) {
        printMessage(ERROR, message.toString());
    }

    private static void printMessage(errorType errorType, String message)  {
        switch (errorType) {
            case TRACE:
                System.out.println(new Date() + " TRACE " + message);
                break;
            case DEBUG:
                System.out.println(new Date().toString() + " DEBUG " + message);
                break;
            case INFO:
                System.out.println(GREEN + new Date().toString() + " INFO " + message + RESET);
                break;
            case WARN:
                System.out.println(YELLOW + new Date().toString() + " WARN " + message + RESET);
                break;
            case ERROR:
                System.out.println(RED + new Date().toString() + " ERROR " + message + RESET);
                break;
        }
    }
}

