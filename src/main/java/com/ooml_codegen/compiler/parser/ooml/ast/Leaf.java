package com.ooml_codegen.compiler.parser.ooml.ast;

import com.ooml_codegen.compiler.lexer.Token;

import java.util.ArrayList;

public class Leaf extends Node {

    public Leaf(Token token, NodeType nodeType){
        super(nodeType);
    }
    public ArrayList<InnerNode> getChildren(){
        return null;
    }

}
