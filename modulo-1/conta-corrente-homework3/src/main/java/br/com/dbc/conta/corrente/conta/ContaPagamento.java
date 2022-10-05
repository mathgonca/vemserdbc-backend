package br.com.dbc.conta.corrente.conta;

import br.com.dbc.conta.corrente.cliente.Cliente;
import br.com.dbc.conta.corrente.conta.interfaces.Impressao;

public class ContaPagamento extends Conta implements Impressao {
    static final double TAXA_SAQUE = 4.25;

    public ContaPagamento() {
    }

    public ContaPagamento(Cliente cliente, String numeroConta, int agencia, double saldo) {
        super(cliente, numeroConta, agencia, saldo);
    }

    @Override
    public boolean sacar(double valor) {
        double saldo = this.getSaldo();
        boolean isSaldoSuficiente = saldo >= valor + TAXA_SAQUE;
        boolean isValorPositivo = valor > 0;

        if (isSaldoSuficiente && isValorPositivo) {
            this.setSaldo(saldo - valor);
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

        System.out.println("Conta pagamento: ");
        System.out.println("=".repeat(20));
        System.out.println("Número da conta: " + numeroConta + " Agência: " + agencia);
        System.out.println("Saldo: R$" + String.format("%.2f", saldo));
        System.out.println("");
    }
}
