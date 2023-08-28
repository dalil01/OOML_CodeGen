package com.ooml.codegen.templater;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public abstract class Templater {

	private final VelocityEngine engine;
	private Template template;
	private VelocityContext context;

	protected Templater() {
		this.engine = new VelocityEngine();
		this.engine.setProperty("resource.loader", "classpath");
		this.engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		this.engine.init();
	}

	protected void setTemplate(String filePath) {
		this.template = this.engine.getTemplate("generation/" + filePath);
		this.context = new VelocityContext();
	}

	protected void putTemplateContext(String key, Object value) throws Exception {
		if (this.template == null) {
			throw new Exception("No template found. Please set template before.");
		}

		this.context.put(key, value);
	}

}
