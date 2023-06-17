package com.ooml_codegen.generator;

import com.ooml_codegen.generator.interfaces.IGeneration;

public abstract class Generator {

	protected Generator() {

	}

	public abstract void generate(IGeneration obj);

}
