package com.ooml_codegen.compiler.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.BiConsumer;

public abstract class Lexer {

    private final File file;

    protected final CharStream cStream;
    protected int charN = 0;
    protected int lineN = 0;

    protected Lexer(File file) throws FileNotFoundException {
        this.file = file;
        this.cStream = new CharStream(this.file);
    }

    public File getFile(){
        return this.file;
    }

    public abstract Token nextToken();

    public void forEach(BiConsumer<Integer, Token> action) {
        Token token = this.nextToken();

        int i = 1;
        while (token.getType() != TokenType.EOF) {
            action.accept(i, token);
            token = this.nextToken();
            i++;
        }
    }

}
