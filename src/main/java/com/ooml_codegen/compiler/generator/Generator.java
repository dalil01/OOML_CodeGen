package com.ooml_codegen.compiler.generator;

import com.ooml_codegen.compiler.generator.interfaces.IGeneration;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Generator {

	private final VelocityEngine engine;
	private Template template;
	private VelocityContext context;

	protected Generator() {
		this.engine = new VelocityEngine();
		this.engine.setProperty("resource.loader.classpath.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		this.engine.setProperty("resource.loaders", "classpath");
		this.engine.init();
	}

	public abstract void generate(IGeneration obj);

	protected void setTemplate(String filePath) {
		this.template = this.engine.getTemplate("generation/" + filePath);
		this.context = new VelocityContext();
	}

	protected void addContextToTemplate(String key, Object value) {
		if (this.template == null) {
			System.out.println("No template found. Please set template before.");
			return;
		}

		this.context.put(key, value);
	}

	protected void generateFile(String filePath) {
		if (this.context == null) {
			System.out.println("No context found.");
			return;
		}

		try (FileWriter writer = new FileWriter(filePath)) {
			this.template.merge(this.context, writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
