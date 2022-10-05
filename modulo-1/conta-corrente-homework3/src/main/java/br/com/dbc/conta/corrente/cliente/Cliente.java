package br.com.dbc.conta.corrente.cliente;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private ArrayList<Contato> contatos;
    private ArrayList<Endereco> enderecos;


    public Cliente() {
    }

    public Cliente(String nome, String cpf, ArrayList<Contato> contatos, ArrayList<Endereco> enderecos) {
        this.nome = nome;
        this.cpf = cpf;
        this.contatos = contatos;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void imprimirContatos() {
        if (contatos != null) {
            System.out.println("CONTATOS");
            System.out.println("=".repeat(20));
            for (Contato contato : contatos) {
                if (contato != null) {
                    contato.imprimirContato();
                }
            }
        }
    }

    public void imprimirEnderecos() {
        if (contatos != null) {
            System.out.println("\nENDERECOS");
            System.out.println("=".repeat(20));
            for (Endereco endereco : enderecos) {
                if (endereco != null) {
                    endereco.imprimirEndereco();
                }
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
