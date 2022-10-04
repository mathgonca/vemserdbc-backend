package br.com.dbc.conta.corrente.conta;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.conta.interfaces.Impressao;

public class ContaPoupanca extends Conta implements Impressao {
    static final double JUROS_MENSAL = 1.01;

    public ContaPoupanca() {
    }

    public ContaPoupanca(Cliente cliente, String numeroConta, int agencia, double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    @Override
    public boolean sacar(double valor) {
        double saldo = this.getSaldo();

        if (saldo >= valor && valor > 0) {
            this.setSaldo(saldo - valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean depositar(double valor) {
        double saldo = this.getSaldo();

        if (valor > 0) {
            this.setSaldo(saldo + valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transferir(Conta contaDestino, double valor) {
        double saldoDestino = contaDestino.getSaldo();
        double saldoRemetente = this.getSaldo();

        if (saldoRemetente > valor && valor > 0) {
            contaDestino.setSaldo(saldoDestino + valor);
            this.setSaldo(saldoRemetente - valor);

            return true;
        } else {
            return false;
        }
    }
    @Override
    public void imprimir() {
        String numeroConta = this.getNumeroConta();
        int agencia = this.getAgencia();
        double saldo = this.getSaldo();

        System.out.println("Conta corrente: ");
        System.out.println("=".repeat(20));
        System.out.println("Número da conta: " + numeroConta + " Agência: " + agencia);
        System.out.println("Saldo: R$" + String.format("%.2f", saldo));
        System.out.println("");
    }

    public void creditarTaxa() {
        double saldo = this.getSaldo();

        this.setSaldo(saldo * JUROS_MENSAL);
    }
}
