package com.ooml_codegen.compiler.parser.ooml.ast.node.leaf;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.parser.ooml.ast.Leaf;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;

public class T_PACKAGE extends Leaf {
    public T_PACKAGE(Token token){
        super (token, NodeType.T_PACKAGE);
    }
}
