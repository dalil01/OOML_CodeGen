package com.ooml_codegen.utils;

import java.time.LocalDateTime;

import com.ooml_codegen.utils.enums.ColorCode;
import com.ooml_codegen.utils.enums.ErrorType;

public class ULogger {

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

        errorMessage.append(LocalDateTime.now());

        switch (errorType) {
            case TRACE -> errorMessage.append(" ").append(ErrorType.TRACE).append(" ").append(message);
            case DEBUG -> errorMessage.append(" ").append(ErrorType.DEBUG).append(" ").append(message);
            case INFO -> errorMessage.insert(0, ColorCode.GREEN).append(" ").append(ErrorType.INFO).append(" ").append(message).append(ColorCode.RESET);
            case WARN -> errorMessage.insert(0, ColorCode.YELLOW).append(" ").append(ErrorType.WARN).append(" ").append(message).append(ColorCode.RESET);
            case ERROR -> errorMessage.insert(0, ColorCode.RED).append(" ").append(ErrorType.ERROR).append(" ").append(message).append(ColorCode.RESET);
        }

        System.out.println(errorMessage);
    }

}
