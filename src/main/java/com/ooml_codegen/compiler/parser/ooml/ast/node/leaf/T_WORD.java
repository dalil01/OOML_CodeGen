package com.ooml_codegen.compiler.parser.ooml.ast.node.leaf;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.lexer.TokenType;
import com.ooml_codegen.compiler.parser.ooml.ast.Leaf;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;

public class T_WORD extends Leaf {
    public T_WORD(Token token){
        super(token, NodeType.T_WORD);
    }

    public boolean isValid(){
        return (token.getType() == TokenType.WORD || token.getType() == TokenType.QUOTED_WORD);
    }
}
