package br.com.dbc.conta.corrente;

public class Cliente {
    public String nome;
    public String cpf;
    public Contato[] contatos = new Contato[2];
    public Endereco[] enderecos = new Endereco[2];

    public void imprimirContatos() {
        System.out.println("CONTATOS");
        System.out.println("=".repeat(20));
        for (Contato contato : contatos) {
            if (contato != null) {
                contato.imprimirContato();
            }
        }
    }

    public void imprimirEnderecos() {
        System.out.println("\nENDERECOS");
        System.out.println("=".repeat(20));
        for (Endereco endereco : enderecos) {
            if (endereco != null) {
                endereco.imprimirEndereco();
            }
        }
    }

    public void imprimirCliente() {
        System.out.println("\nCLIENTE");
        System.out.println("=".repeat(20));
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }
}
