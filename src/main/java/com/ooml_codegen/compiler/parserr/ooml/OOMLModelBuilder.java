package com.ooml_codegen.compiler.parserr.ooml;

import com.ooml_codegen.models.Package;

public class OOMLModelBuilder {

	private Package aPackage;

	public OOMLModelBuilder() {
		this.init();
	}

	private void init() {
		this.resetData();
	}

	public void resetData() {
		this.aPackage = null;
	}

	private boolean isPackageBuilt() {
		return this.aPackage != null;
	}

}
