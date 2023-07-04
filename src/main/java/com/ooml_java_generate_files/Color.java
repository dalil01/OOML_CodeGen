package com.ooml_java_generate_files;


public enum Color {
    RED("red"),
	ORANGE("orange"),
	YELLOW("yellow"),
	GREEN("green"),
	BLUE;

    private final String value;

    Color(String value){ this.value = value; }

    Color() { this.value = ""; }

    public String getValue() { return this.value; }


}