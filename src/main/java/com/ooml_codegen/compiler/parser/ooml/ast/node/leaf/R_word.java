package com.ooml_codegen.compiler.parser.ooml.ast.node.leaf;

import com.ooml_codegen.compiler.lexer.Token;
import com.ooml_codegen.compiler.parser.ooml.ast.Leaf;
import com.ooml_codegen.compiler.parser.ooml.ast.Node;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;

public class R_word extends Leaf {
    public R_word(Token token){
        super(token, NodeType.R_word);
    }

}
