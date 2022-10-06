package homework;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<Pessoa> lista = new ArrayList<>();
        int i = 0;
        lista.add(new Pessoa(++i, "Paulo", 6500, "Desenvolvedor"));
        lista.add(new Pessoa(++i, "Pedro Paulo", 5300, "Desenvolvedor"));
        lista.add(new Pessoa(++i, "Enzo", 2350, "Desenvolvedor"));
        lista.add(new Pessoa(++i, "Joel", 6000, "Arquiteto"));
        lista.add(new Pessoa(++i, "Henrique", 1000, "Estagiário"));
        lista.add(new Pessoa(++i, "Gabriel", 1000, "Estagiário"));
        lista.add(new Pessoa(++i, "Gustavo", 18000, "Diretor"));

        //1- listar todas as pessoas
        System.out.println("\n1 - Todas as pessoas:");
        System.out.println("=".repeat(30));
        System.out.println("");
        lista.stream()
                .forEach(System.out::println);
//                .forEach(pessoa -> System.out.println(pessoa.getNome() + " "));

        //2- filtrar todas as pessoas com salario maior do que 5 mil (filter)
        System.out.println("\n2 - Pessoas que tem o sálario maior que R$5000");
        System.out.println("=".repeat(30));
        System.out.println("");

        lista.stream()
                .filter(pessoa -> pessoa.getSalario() > 5000)
                .forEach(System.out::println);

        //3- filtrar todas as pessoas que são desenvolvedoras e organizar por salário crescente (filter, sorted)
        System.out.println("\n3 - Pessoas que são desenvolvedores organizados por sálario crescente");
        System.out.println("=".repeat(30));
        System.out.println("");

        lista.stream()
                .filter(pessoa -> pessoa.getCargo().equals("Desenvolvedor"))
                .sorted(Comparator.comparingDouble(Pessoa::getSalario))
                .forEach(System.out::println);

        //4- fazer a média salarial de todos
        System.out.println("\n4 - Média de todos os salários");
        System.out.println("=".repeat(30));

        Double media = lista.stream().mapToDouble(Pessoa::getSalario).average().getAsDouble();
        System.out.println("R$" + String.format("%.2f", media));

        //5- verificar na lista (utilizando o método anyMatch) se tem alguém que ganha mais do que 20 mil
        boolean isSalarioMaiorQue20mil = lista.stream()
                .anyMatch(pessoa -> pessoa.getSalario() > 20000);

        System.out.println("\n5 - Tem alguem ganhando mais que R$20 mil na lista?");
        System.out.println("=".repeat(30));
        if (isSalarioMaiorQue20mil) {
            System.out.println("VERDADE, alguem ganha mais que R$20 mil");
        } else {
            System.out.println("FALSO, ninguém alguem ganha mais que R$20 mil");
        }

        //6- retornar uma lista de todos os ids das pessoas
        System.out.println("\n6 = Todos os ids");
        System.out.println("=".repeat(30));
        List<Integer> ids = lista.stream().map(Pessoa::getId).toList();
        ids.stream().forEach(System.out::println);


        //7- criar uma nova classe Salario com ID e Salário, utilizando a função "map" do stream, retornar uma lista desse novo objeto
        System.out.println("\n7 - Lista de salários");
        System.out.println("=".repeat(30));

        List<Salario> salarioList = lista.stream().map(pessoa -> new Salario(pessoa.getId(), pessoa.salario)).toList();
        salarioList.stream()
                .forEach(System.out::println);

        //8- retornar um HashMap (estrutura de dados, e não uma função map) contendo os ids e os nomes dos colaboradores
        System.out.println("\n8 - Hashmap IDs & Nomes");
        System.out.println("=".repeat(30));

        Map<Integer, String> mapIdPessoas = lista.stream()
                .collect(Collectors.toMap(Pessoa::getId, Pessoa::getNome));
        System.out.println(mapIdPessoas);

        //9- com o mapa da 8, retornar o nome com o id=2
        System.out.println("\n9 - Pessoa com Id = 2");
        System.out.println("=".repeat(30));

        System.out.println(mapIdPessoas.get(2));

        //10- verificar se tem alguém que contenha o nome "Paulo" (containsignorecase) na lista e retornar o primeiro elemento que encontrar (findFirst).
        //    Imprimir o nome e salário dessa pessoa
        System.out.println("\n10 - Primeira pessoa com \"Paulo\" no nome nessa lista.");
        System.out.println("=".repeat(30));

        Optional<Pessoa> paulo = lista.stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains("PAULO"))
                .findFirst();

        if (paulo.isPresent()) {
            System.out.println(paulo.get());
        } else {
            System.out.println("Não tem nenhuma pessoa com \"Paulo\" no nome nessa lista.");
        }
    }

    static class Pessoa {
        private int id;
        private String nome;
        private double salario;
        private String cargo;

        public Pessoa(int id, String nome, double salario, String cargo) {
            this.id = id;
            this.nome = nome;
            this.salario = salario;
            this.cargo = cargo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public double getSalario() {
            return salario;
        }

        public void setSalario(double salario) {
            this.salario = salario;
        }

        public String getCargo() {
            return cargo;
        }

        public void setCargo(String cargo) {
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return "id = " + id +
                    ", nome = '" + nome + '\'' +
                    ", salario = R$" + String.format("%.2f", salario) +
                    ", cargo = '" + cargo + '\'';
        }
    }

    static class Salario {
        private int id;
        private double salario;

        public Salario(int id, double salario) {
            this.id = id;
            this.salario = salario;
        }

        @Override
        public String toString() {
            return "id = " + id +
                    ", salario = R$" + String.format("%.2f", salario);
        }
    }
}
