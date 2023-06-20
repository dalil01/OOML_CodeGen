package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Lexer {

    protected final String filePath;

    protected final CharStream cStream;

    protected Lexer(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.cStream = new CharStream(new File(filePath));
    }

    public abstract Token nextToken();
}
