package com.ooml.codegen.utils;

import java.time.LocalDateTime;

public class ULogger {

    public enum ErrorType {

        TRACE("TRACE"),
        DEBUG("DEBUG"),
        INFO("INFO"),
        WARN("WARN"),
        ERROR("ERROR");

        private final String type;

        ErrorType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }

    }

    public enum ColorCode {

        RESET("\033[0m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        RED("\u001B[31m");

        private final String code;

        ColorCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }

    }

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
