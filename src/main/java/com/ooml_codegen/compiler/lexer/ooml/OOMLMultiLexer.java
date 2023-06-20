package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.utils.Logger;
import com.ooml_codegen.utils.UFile;

import java.io.File;
import java.util.List;
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
        List<String> files = UFile.findOOMLFilesPath(importToken.getStringValue());
        if (files.isEmpty()) {
            Logger.error("Couldn't import " + importToken.getStringValue());
            throw new FileNotFoundException("Couldn't import " + importToken.getStringValue());
        }
        for (String filePath: files){
            stack.push(new OOMLLexer(filePath));
        }
    }

}
