package br.com.zupedu.lgpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.stemmer.PorterStemmer;

public class Vocabulario {
        private static List<String> pessoal;

        // private static final List<String> sensivel = Arrays.asList("nome", "email",
        // "cpf", "rg", "cpf", "endereco", "telefone");

        private static final String stemming(String s) {
                return new PorterStemmer().stem(s).toString();
        }

        // todo: buscar um stemming de portugues
        public static final List<String> pessoal() {
                if (pessoal == null) {
                        pessoal = new ArrayList<>();
                        String[] dados_pessoais = { "nome", "email", "rg",
                                        "cpf", "endereco", "telefone", "cep", "cidade", "estado" };

                        Arrays.asList(dados_pessoais)
                                        .forEach((dado) -> pessoal.add(stemming(dado)));
                }
                return pessoal;
        }

        public static void main(String[] args) {
                System.out.println(pessoal());
        }

}
