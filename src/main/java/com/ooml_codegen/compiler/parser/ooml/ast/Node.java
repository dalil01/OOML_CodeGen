package com.ooml_codegen.compiler.parser.ooml.ast;

import com.ooml_codegen.compiler.lexer.Token;

import java.util.ArrayList;

public class Node {
    protected Token token;
    protected NodeType nodeType;

    protected ArrayList<Node> left;
    protected ArrayList<Node> right;

    public Node(NodeType nodeType){
        this.token = null;
        this.nodeType = nodeType;
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();
    }

    public Token getToken(){
        return this.token;
    }

    public NodeType getNodeType(){
        return this.nodeType;
    }

    public ArrayList<Node> getLeft(){
        return this.left;
    }

    public ArrayList<Node> getRight() {
        return this.right;
    }
}
