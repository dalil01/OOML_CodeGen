package com.ooml_codegen.compiler.parser.ooml.ast.node.leaf;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.parser.ooml.ast.Leaf;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;

public class T_ENUM extends Leaf {
    public T_ENUM(Token token){
        super(token, NodeType.T_ENUM);
    }
}
