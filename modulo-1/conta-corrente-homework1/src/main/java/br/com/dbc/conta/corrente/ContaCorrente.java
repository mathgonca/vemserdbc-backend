package br.com.dbc.conta.corrente;

public class ContaCorrente {
    public Cliente cliente;
    public String numeroConta;
    public int agencia;
    public double saldo;
    public double chequeEspecial;

    public void imprimirContaCorrente() {
        double chequeEspecialDisponivel = 0;

        if (saldo < 0) {
            chequeEspecialDisponivel = chequeEspecial - saldo;
        } else {
            chequeEspecialDisponivel = chequeEspecial;
        }

        System.out.println("Conta corrente: ");
        System.out.println("=".repeat(20));
        System.out.println("Número da conta: " + numeroConta + " Agência: " + agencia);
        System.out.println("Saldo: R$" + String.format("%.2f", saldo) + " Cheque Especial: R$" + String.format("%.2f", chequeEspecialDisponivel));
        System.out.println("");
    }

    public boolean sacar(double valor) {
        double saldoCache = retornarSaldoComChequeEspecial();

        if (saldoCache > valor) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean depositar(double valor) {
        double saldoAntesDoDeposito = saldo;
        saldo += valor;

        if (saldo == saldoAntesDoDeposito + valor) {
            return true;
        } else {
            return false;
        }
    }

    public double retornarSaldoComChequeEspecial() {
        return chequeEspecial + saldo;
    }

    public boolean transferir(ContaCorrente contaCorrente, double valor) {
        double saldoRemetente = retornarSaldoComChequeEspecial();

        if (saldoRemetente > valor) {
            contaCorrente.saldo += valor;
            saldo -= valor;

            return true;
        } else {
            return false;
        }
    }
}
