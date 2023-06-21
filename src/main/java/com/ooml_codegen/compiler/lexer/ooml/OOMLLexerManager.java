package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.LexerManager;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.utils.Logger;
import com.ooml_codegen.utils.UFile;

import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;

public class OOMLLexerManager extends LexerManager {

    public OOMLLexerManager(File file)  throws FileNotFoundException {
        super(new OOMLLexer(file));
    }

    protected void manageImport(Token importToken) throws FileNotFoundException {
        // Stack should never be empty when entering this function (how else would we have gotten an import token?)
        assert this.stack.size() != 0;
        assert this.stack.peek() != null;
        if (importToken.getStringValue().isEmpty()){
            Logger.warn("Empty import token at " + importToken.getLocation());
            return;
        }

        List<File> files = UFile.findOOMLFilesPath(importToken.getStringValue(), this.stack.peek().getFile());

        if (files.isEmpty()) {
            Logger.error("Couldn't import " + importToken.getStringValue() + " at " + importToken.getLocation());
            throw new FileNotFoundException("Couldn't import " + importToken.getLocation());
        }

        for (File file: files){
            this.stack.push(new OOMLLexer(file));
        }
    }

}
