package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Lexer {

    private final File file;

    protected final CharStream cStream;

    protected Lexer(File file) throws FileNotFoundException {
        this.file = file;
        this.cStream = new CharStream(this.file);
    }

    public File getFile(){
        return this.file;
    }

    public abstract Token nextToken();
}
