package br.com.zupedu.lgpd;

import java.util.List;

import javax.persistence.Entity;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;

public class ParsingClassesDaHandora {

    public static void main(String[] args) {
        Launcher spoon = new Launcher();
        // todo: parametrizar isso no futuro
        spoon.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/");
        spoon.buildModel();

        CtModel model = spoon.getModel();
        for (CtType clazz : model.getAllTypes()) {
            System.out.println("Achei a classe: " + clazz.getSimpleName());
            if (clazz.getAnnotation(Entity.class) != null) {
                System.out.println("A classe '" + clazz.getSimpleName() + "' classe de acesso a banco!");

                List<CtField> fields = model
                        .filterChildren(new TypeFilter<>(CtField.class))
                        .list();
                for (CtField field : fields) {
                    boolean contemDadosSensiveis = Vocabulario.pessoal()
                            .stream()
                            .anyMatch(field.getSimpleName()::contains);
                    if (contemDadosSensiveis) {
                        System.out.println("Possível attr sensível encontrado: " + field.getSimpleName());
                    }
                }

                List<CtMethod> methods = model
                        .filterChildren(new TypeFilter<>(CtMethod.class))
                        .list();
                for (CtMethod method : methods) {
                    boolean contemDadosSensiveis = Vocabulario.pessoal()
                            .stream()
                            .anyMatch(method.getSimpleName().toLowerCase()::contains);

                    if (contemDadosSensiveis) {
                        System.out.println("Possível método sensível encontrado: " + method.getSimpleName());
                    }
                }

            }
        }
    }
}