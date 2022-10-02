package br.com.dbc.conta.corrente;

public class Main {
    public static void main(String[] args) {
        Endereco endereco01Comercial = new Endereco();
        endereco01Comercial.tipo = 2;
        endereco01Comercial.logradouro = "R. teste";
        endereco01Comercial.numero = 123;
        endereco01Comercial.complemento = "apto 456";
        endereco01Comercial.cep = "90000-111";
        endereco01Comercial.cidade = "Teste do sul";
        endereco01Comercial.estado = "RS";
        endereco01Comercial.pais = "Brasil";

        Contato contato01Comercial = new Contato();
        contato01Comercial.descricao = "Descrição teste";
        contato01Comercial.telefone = "51912345678";
        contato01Comercial.tipo = 1;

        Contato contato01Residencial = new Contato();
        contato01Residencial.descricao = "Descrição teste";
        contato01Residencial.telefone = "51987654321";
        contato01Residencial.tipo = 2;

        Cliente cliente01 = new Cliente();
        cliente01.nome = "João";
        cliente01.cpf = "12345678900";
        cliente01.contatos[0] = contato01Comercial;
        cliente01.contatos[1] = contato01Residencial;
        cliente01.enderecos[0] = endereco01Comercial;

        ContaCorrente contaCorrente01 = new ContaCorrente();
        contaCorrente01.cliente = cliente01;
        contaCorrente01.numeroConta = "12345";
        contaCorrente01.agencia = 8888;
        contaCorrente01.saldo = 3500;

        Cliente cliente02 = new Cliente();
        cliente02.nome = "Maria";
        cliente02.cpf = "98765432100";

        ContaCorrente contaCorrente02 = new ContaCorrente();
        contaCorrente02.cliente = cliente02;
        contaCorrente02.numeroConta = "99999";
        contaCorrente02.agencia = 8888;
        contaCorrente02.saldo = 10000;

        contaCorrente01.depositar(600.5);
        contaCorrente01.transferir(contaCorrente02, 200);

        contaCorrente02.sacar(100);

        cliente01.imprimirCliente();
        contaCorrente01.imprimirContaCorrente();
        cliente01.imprimirContatos();
        cliente01.imprimirEnderecos();
    }
}
