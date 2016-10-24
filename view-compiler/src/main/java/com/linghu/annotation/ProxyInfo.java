package com.linghu.annotation;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * created by linghu on 2016/10/10
 *
 */
public class ProxyInfo {
    private String packageName;
    private String proxyClassName;
    private TypeElement typeElement;

    public Map<Integer, VariableElement> injectVariables = new HashMap<>();
    public Map<ExecutableElement, int[]> injectMethods = new HashMap<>();

    public static final String PROXY = "ViewInject";

    public ProxyInfo(Elements elementUtils, TypeElement classElement) {
        this.typeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        //classname
        String className = ClassValidator.getClassName(classElement, packageName);
        this.packageName = packageName;
        this.proxyClassName = className + "$$" + PROXY;
    }


    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("/* \n");
        builder.append(" * Copyright (C) 2016 linghu\n");
        builder.append(" * Anotation generated codes.Do not modify!\n*/\n");

        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import com.linghu.annotation.*;\n");
        builder.append("import android.view.View;\n");
        builder.append("import android.view.View.*;\n");
        builder.append('\n');

        builder.append("public final class ").append(proxyClassName).append(" implements " + ProxyInfo.PROXY + "<" + typeElement.getQualifiedName() + ">");
        builder.append(" {\n");

        generateMethods(builder);
        builder.append('\n');

        builder.append("\n");
        builder.append("}\n");
        return builder.toString();
    }

    private void generateMethods(StringBuilder builder) {
        builder.append("\n");
        builder.append("    @Override\n ");
        builder.append("    public void inject(final " + typeElement.getQualifiedName() + " host, Object source) {\n");

        builder.append("        if(source instanceof android.app.Activity){\n");
        for (int id : injectVariables.keySet()) {
            VariableElement element = (VariableElement) injectVariables.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append("            host." + name).append(" = ");
            builder.append("(" + type + ")(((android.app.Activity)source).findViewById(" + id + "));\n");
        }
        for (final ExecutableElement methodElement : injectMethods.keySet()) {
            int[] ids = injectMethods.get(methodElement);
            String name = methodElement.getSimpleName().toString();
            for (int itemId : ids) {
                builder.append("            View view" + itemId + " = (((android.app.Activity)source).findViewById(" + itemId + "));\n");
                builder.append("            view" + itemId + ".setOnClickListener(new View.OnClickListener() { \n");
                builder.append("                public void onClick(View v) {\n");
                builder.append("                    host." + name + "(v);\n");
                builder.append("                }\n");
                builder.append("            });\n");
            }
            String type = methodElement.asType().toString();
        }
        builder.append("        }else{\n");
        for (int id : injectVariables.keySet()) {
            VariableElement element = (VariableElement) injectVariables.get(id);
            String name = element.getSimpleName().toString();
            String type = element.asType().toString();
            builder.append("            host." + name).append(" = ");
            builder.append("(" + type + ")(((android.view.View)source).findViewById(" + id + "));\n");
        }
        for (final ExecutableElement methodElement : injectMethods.keySet()) {
            int[] ids = injectMethods.get(methodElement);
            String name = methodElement.getSimpleName().toString();
            for (int itemId : ids) {
                builder.append("            View view" + itemId + " = (((android.view.View)source).findViewById(" + itemId + "));\n");
                builder.append("            view" + itemId + ".setOnClickListener(new View.OnClickListener() { \n");
                builder.append("                public void onClick(View v) {\n");
                builder.append("                    host." + name + "(v);\n");
                builder.append("                }\n");
                builder.append("            });\n");
            }
            String type = methodElement.asType().toString();
        }
        builder.append("        }\n");
//
//
//        builder.append("        if(source instanceof android.app.Activity){\n");
//
//        builder.append("        }else{\n");
//
//        builder.append("        }\n");
        builder.append("    }\n");
    }

    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }


}