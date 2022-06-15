package br.com.zupedu.lgpd;

import java.util.Set;

import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.path.CtRole;
import spoon.reflect.reference.CtFieldReference;
import spoon.support.reflect.declaration.CtMethodImpl;

public class HelloWorld {

    public static void main(String[] args) {
        CtClass l = Launcher.parseClass("class A { void m() { System.out.println(\"yeah\");} }");


        System.out.println("Atributos: ");

        for (CtFieldReference field : l.getAllFields()) {
            System.out.println(field.getSimpleName());
        }

        System.out.println("Metodos:");
        Set<CtMethodImpl> methods = l.getValueByRole(CtRole.METHOD);
        for (CtMethodImpl method : methods) {
            System.out.println(method.getSimpleName());
        }

    }

}
