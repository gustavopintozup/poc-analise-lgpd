package br.com.zupedu.lgpd;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.filter.TypeFilter;

public class ParsingHandoraClass {
    public static void main(String[] args) {

        Launcher spoon = new Launcher();
        spoon.addInputResource(
                "/home/gustavopinto/workspace/plataforma-treino-lms/src/main/java/br/com/zup/lms/alunos/Aluno.java");

        spoon.buildModel();
        CtModel model = spoon.getModel();

        for (CtType aluno : model.getAllTypes()) {
            if (aluno.getAnnotation(Entity.class) != null) {
                System.out.println("É uma classe de acesso a banco!");
            }
        }

        List<CtField> fields = model.filterChildren(new TypeFilter<>(CtField.class)).list();
        for (CtField field : fields) {
            boolean contemDadosSensiveis = Arrays.stream(Vocabulario.sensiveis).anyMatch(field.getSimpleName()::contains);
            if(contemDadosSensiveis) {
                System.out.println("Atributo com dado sensível encontrado: " + field.getSimpleName());

            }
        }

        System.out.println("\nMETHODS");
        List<CtMethod> methods = model.filterChildren(new TypeFilter<>(CtMethod.class)).list();
        for (CtMethod method : methods) {
            boolean contemDadosSensiveis = Arrays.stream(Vocabulario.sensiveis).anyMatch(method.getSimpleName().toLowerCase()::contains);
            if(contemDadosSensiveis) {
                System.out.println("Metodo com dado sensível encontrado: " + method.getSimpleName());

            }
        }

    }
}