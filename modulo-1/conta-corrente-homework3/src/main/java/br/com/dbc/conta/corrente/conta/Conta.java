package br.com.dbc.conta.corrente.conta;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.conta.interfaces.Movimentacao;

public abstract class Conta implements Movimentacao {
    private Cliente cliente;
    private String numeroConta;
    private int agencia;
    private double saldo;

    public Conta() {
    }

    public Conta(Cliente cliente, String numeroConta, int agencia, double saldo) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
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
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
