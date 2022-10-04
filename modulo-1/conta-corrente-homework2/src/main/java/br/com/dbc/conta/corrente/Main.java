package br.com.dbc.conta.corrente;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.cliente.Contato;
import br.com.dbc.conta.corrente.cliente.Endereco;
import br.com.dbc.conta.corrente.conta.ContaCorrente;
import br.com.dbc.conta.corrente.conta.ContaPoupanca;

public class Main {
    public static void main(String[] args) {
        Endereco endereco01Comercial = new Endereco(2, "R. teste", 123,
                "apto 456", "90000-111", "Teste do sul", "RS", "Brasil");

        Endereco[] enderecos01 = new Endereco[2];
        enderecos01[0] = endereco01Comercial;

        Contato contato01Comercial = new Contato("Descrição teste", "51912345678", 1);
        Contato contato01Residencial = new Contato("Descrição teste", "51987654321", 2);

        Contato[] contatos01 = {contato01Comercial, contato01Residencial};

        Cliente cliente01 = new Cliente("João", "12345678900", contatos01, enderecos01);

        ContaCorrente contaCorrente01 = new ContaCorrente(cliente01, "12345", 8888, 3500, 0);

        Cliente cliente02 = new Cliente();
        cliente02.setNome("Maria");
        cliente02.setNome("98765432100");

        ContaCorrente contaCorrente02 = new ContaCorrente(cliente02, "99999", 8888, 10000, 0);

        contaCorrente01.depositar(600.5);
        contaCorrente01.transferir(contaCorrente02, 200);

        contaCorrente02.sacar(100);

        ContaPoupanca contaCorrente02Poupanca = new ContaPoupanca(cliente02, "99999-0", 8888, 450);
        contaCorrente02Poupanca.creditarTaxa();

        cliente01.imprimirCliente();
        contaCorrente01.imprimir();
        cliente01.imprimirContatos();
        cliente01.imprimirEnderecos();

        contaCorrente02Poupanca.imprimir();
    }
}
