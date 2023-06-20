package com.ooml_codegen.compiler.lexer.ooml;

import com.ooml_codegen.compiler.lexer.Lexer;
import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.utils.Logger;
import com.ooml_codegen.utils.UFile;

import java.util.List;
import java.io.FileNotFoundException;

public class OOMLLexer extends Lexer {

    public OOMLLexer(String file)  throws FileNotFoundException {
        super(new OOMLSingleLexer(file));
    }

    protected void manageImport(Token importToken) throws FileNotFoundException {
        List<String> files = UFile.findOOMLFilesPath(importToken.getStringValue());
        if (files.isEmpty()) {
            Logger.error("Couldn't import " + importToken.getStringValue());
            throw new FileNotFoundException("Couldn't import " + importToken.getStringValue());
        }
        for (String filePath: files){
            stack.push(new OOMLSingleLexer(filePath));
        }
    }

}
