package com.ooml_java_generate_files;


public enum Color {
    RED("red"),
	ORANGE("orange"),
	YELLOW("yellow"),
	GREEN("green"),
	WEIGH("84.5"),
	BLUE;
    private final Object value;
    Color(Object value){ this.value = value; }

    Color() { this.value = ordinal(); }

    public Object getValue() { return this.value; }
}