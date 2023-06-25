package com.ooml_codegen.compiler.parser.ooml.ast.node.leaf;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.parser.ooml.ast.Node;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;

public class R_restricted_access_modifier extends Node {
    public R_restricted_access_modifier(Token token){
        super(token, NodeType.R_restricted_access_modifier);
    }
}
