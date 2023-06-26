package com.ooml_codegen.compiler.parser.ooml.ast.node;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.parser.ooml.ast.InnerNode;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;
import com.ooml_codegen.compiler.parser.ooml.ast.node.leaf.T_WORD;

public class R_modifier extends InnerNode {
    public R_modifier(){
        super(NodeType.R_modifier);
    }

    public boolean isValid(Token token) {
        InnerNode child = new T_WORD(token);
        if (child.isValid()) {
            this.children.add(child);
            return true;
        }
        return false;
    }
}
