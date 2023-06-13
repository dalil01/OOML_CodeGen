package com.ooml_codegen.utils;

import java.util.Date;

import com.ooml_codegen.utils.enums.ColorCode;
import com.ooml_codegen.utils.enums.ErrorType;

public class Logger {

    public static void trace(String message) {
        printMessage(ErrorType.TRACE, message);
    }

    public static void debug(String message) {
        printMessage(ErrorType.DEBUG, message);
    }

    public static void info(String message) {
        printMessage(ErrorType.INFO, message);
    }

    public static void warn(String message) {
        printMessage(ErrorType.WARN, message);
    }

    public static void error(String message) {
        printMessage(ErrorType.ERROR, message);
    }

    private static void printMessage(ErrorType errorType, String message)  {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append(new Date());

        switch (errorType) {
            case TRACE:
                errorMessage.append(" TRACE ");
                break;
            case DEBUG:
                errorMessage.append(" DEBUG ");
                break;
            case INFO:
                errorMessage.insert(0, ColorCode.GREEN).append(" INFO ").append(message).append(ColorCode.RESET);
                break;
            case WARN:
                errorMessage.insert(0, ColorCode.YELLOW).append(" WARN ").append(message).append(ColorCode.RESET);
                break;
            case ERROR:
                errorMessage.insert(0, ColorCode.RED).append(" ERROR ").append(message).append(ColorCode.RESET);
                break;
        }
        System.out.println(errorMessage);
    }
}

