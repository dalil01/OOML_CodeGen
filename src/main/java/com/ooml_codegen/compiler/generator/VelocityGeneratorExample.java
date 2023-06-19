package com.ooml_codegen.compiler.generator;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;

public class VelocityGeneratorExample {

    public static void main(String[] args) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "John Doe");
        velocityContext.put("date", "19/06/2023");

        VelocityEngine velocityEngine = new VelocityEngine();
//        velocityEngine.setProperty("resource.loader.classpath.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        velocityEngine.setProperty("resource.loaders", "classpath");
        velocityEngine.setProperty("resource.loader", "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        Template template = velocityEngine.getTemplate("VelocityTemplateExample.vm");

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);

        System.out.println(writer.toString());
    }

}
