package com.ooml_codegen.utility.logger;

import java.util.Date;

public class CodegenLogger {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";


    public void trace(String message) {
        System.out.println(new Date().toString() + " TRACE " + message);
    }

    public void debug(String message) {
        System.out.println(new Date().toString() + " DEBUG " + message);
    }

    public void info(String message) {
        System.out.println(GREEN + new Date().toString() + " INFO " + message + RESET);
    }


    public void warn(String message) {
        System.out.println(YELLOW + new Date().toString() + " WARN " + message + RESET);
    }


    public void error(String message) {
        System.out.println(RED + new Date().toString() + " ERROR " + message + RESET);
    }
}
