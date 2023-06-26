package com.ooml_codegen.compiler.parser.ooml.ast.node;

import com.ooml_codegen.compiler.parser.ooml.ast.Leaf;
import com.ooml_codegen.compiler.parser.ooml.ast.InnerNode;
import com.ooml_codegen.compiler.parser.ooml.ast.NodeType;
import com.ooml_codegen.compiler.parser.ooml.ast.node.leaf.T_PACKAGE;

public class R_package extends InnerNode {
    public R_package(){
        super(NodeType.R_package);
    }

    @Override
    public boolean isValid() {
        // heckck next token
        Leaf package_sign = new T_PACKAGE(nextToken());
        int i = package_sign.initToken();
        if (!package_sign.isValid()){
            return false;
        }
        this.children.add(package_sign);

        InnerNode package_name = new R_word();


    }
}
