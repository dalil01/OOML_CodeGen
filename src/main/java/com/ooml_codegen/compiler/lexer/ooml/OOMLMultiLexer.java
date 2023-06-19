package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

public class OOMLMultiLexer {

    Deque<OOMLLexer> stack;
    public OOMLMultiLexer(String file) throws FileNotFoundException {
        this.stack = new ArrayDeque<>();
        this.stack.push(new OOMLLexer(file));
    }

    public Token next() throws FileNotFoundException{
        if (!stack.isEmpty()){
            Token tok = this.stack.peek().nextToken();

            while (tok.getType() == TokenType.EOF){
                this.stack.pop();

                if (this.stack.isEmpty()) {
                    break;
                } else {
                    tok = this.stack.peek().nextToken();
                }
            }

            if (tok.getType() == TokenType.IMPORT){
                manageImport(tok);
                return next();
            }

            return tok;
        }
        return new Token(TokenType.EOF);
    }

    private void manageImport(Token importToken) throws FileNotFoundException {
        if (isDirectory(importToken.getStringValue())){
            importDirectory(importToken.getStringValue());
        }
        else {
            importSingleFile(new File(importToken.getStringValue()));
        }
    }

    private void importSingleFile(File file) throws FileNotFoundException {
        // TODO
    }

    private boolean isDirectory(String path){
        // TODO
        return false;
    }

    private void importDirectory(String path) throws FileNotFoundException {
        // TODO
    }


}
