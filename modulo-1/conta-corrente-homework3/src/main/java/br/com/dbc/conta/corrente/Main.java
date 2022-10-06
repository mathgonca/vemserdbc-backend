package br.com.dbc.conta.corrente;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.cliente.Contato;
import br.com.dbc.conta.corrente.cliente.Endereco;
import br.com.dbc.conta.corrente.conta.ContaCorrente;
import br.com.dbc.conta.corrente.conta.ContaPagamento;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Endereco endereco01Comercial = new Endereco(2, "R. teste", 123,
                "apto 456", "90000-111", "Teste do sul", "RS", "Brasil");

        ArrayList<Endereco> enderecos01 = new ArrayList<>();
        enderecos01.add(endereco01Comercial);

        Contato contato01Comercial = new Contato("Descrição teste", "51912345678", 1);
        Contato contato01Residencial = new Contato("Descrição teste", "51987654321", 2);

        ArrayList<Contato> contatos01 = new ArrayList<>();
        contatos01.add(contato01Comercial);
        contatos01.add(contato01Residencial);

        Cliente cliente01 = new Cliente("João", "12345678900", contatos01, enderecos01);

        ContaCorrente contaCorrente01 = new ContaCorrente(cliente01, "12345", 8888, 3500, 0);

        Cliente cliente02 = new Cliente();
        cliente02.setNome("Maria");
        cliente02.setCpf("98765432100");

        ContaCorrente contaCorrente02 = new ContaCorrente(cliente02, "99999", 8888, 10000, 0);

        contaCorrente01.depositar(600.5);
        contaCorrente01.transferir(contaCorrente02, 200);

        contaCorrente02.sacar(100);

        ContaPagamento contaPagamento02 = new ContaPagamento(cliente02, "99999-0", 8888, 450);

        contaPagamento02.sacar(350);
        contaPagamento02.sacar(150);

        contaPagamento02.transferir(contaCorrente02, 350);
        contaPagamento02.transferir(contaCorrente02, 50);


        contaCorrente01.imprimir();
        contaCorrente02.imprimir();
        contaPagamento02.imprimir();
    }
}
