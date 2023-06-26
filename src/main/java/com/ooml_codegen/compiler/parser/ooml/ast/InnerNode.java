package com.ooml_codegen.compiler.parser.ooml.ast;

import com.ooml_codegen.compiler.lexer.Token;

import java.util.ArrayList;

public class InnerNode extends Node {

    protected NodeType nodeType;

    protected ArrayList<Node> children;

    public InnerNode(NodeType nodeType){
        super(nodeType);
        this.children = new ArrayList<>();
    }

    public NodeType getNodeType(){
        return this.nodeType;
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    public boolean isValid(){
        return false;
    }

    public boolean isValid(Token token){
        return false;
    }

}
