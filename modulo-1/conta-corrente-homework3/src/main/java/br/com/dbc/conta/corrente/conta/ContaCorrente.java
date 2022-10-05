package br.com.dbc.conta.corrente.conta;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.conta.interfaces.Impressao;

public class ContaCorrente extends Conta implements Impressao {
    public double chequeEspecial;

    public ContaCorrente() {
    }


    public ContaCorrente(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public ContaCorrente(Cliente cliente, String numeroConta, int agencia, double saldo, double chequeEspecial) {
        super(cliente, numeroConta, agencia, saldo);
        this.chequeEspecial = chequeEspecial;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    @Override
    public void imprimir() {
        double chequeEspecialDisponivel = 0;
        String numeroConta = this.getNumeroConta();
        int agencia = this.getAgencia();
        double saldo = this.getSaldo();

        if (saldo < 0) {
            chequeEspecialDisponivel = chequeEspecial - saldo;
        } else {
            chequeEspecialDisponivel = chequeEspecial;
        }

        System.out.println("Conta corrente: ");
        System.out.println("=".repeat(20));
        System.out.println("Número da conta: " + numeroConta + " Agência: " + agencia);
        System.out.println("Saldo: R$" + String.format("%.2f", saldo) + " Cheque Especial: R$"
                + String.format("%.2f", chequeEspecialDisponivel));
        System.out.println("");
    }

    @Override
    public boolean sacar(double valor) {
        double saldo = this.getSaldo();
        double saldoCache = retornarSaldoComChequeEspecial();

        if (saldoCache >= valor && valor > 0) {
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

    public double retornarSaldoComChequeEspecial() {
        return this.getChequeEspecial() + this.getSaldo();
    }

    @Override
    public boolean transferir(Conta contaDestino, double valor) {
        double saldoDestino = contaDestino.getSaldo();
        double saldoRemetenteComChequeEspecial = this.retornarSaldoComChequeEspecial();
        double saldoRemetente = this.getSaldo();

        if (saldoRemetenteComChequeEspecial > valor && valor > 0) {
            contaDestino.setSaldo(saldoDestino + valor);
            this.setSaldo(saldoRemetente - valor);

            return true;
        } else {
            return false;
        }
    }
}
