package br.com.dbc.conta.conta.corrente;

import br.com.dbc.conta.corrente.ContaCorrente;
import org.junit.Assert;
import org.junit.Test;

public class ContaCorrenteTest {
    @Test
    public void deveSacarQuandoValorSaqueForIgualAoSaldo() {
        double saldoEsperado = 0;

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.saldo = 100;

        boolean saque = contaCorrente.sacar(100);

        Assert.assertTrue(saque);
        Assert.assertEquals(saldoEsperado, contaCorrente.saldo, 0.1);
    }

    @Test
    public void deveDepositarQuandoValorForPositivo() {
        double saldoEsperado = 10600;

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.saldo = 10000;

        boolean deposito = contaCorrente.depositar(600);

        Assert.assertTrue(deposito);
        Assert.assertEquals(saldoEsperado, contaCorrente.saldo, 0.1);
    }

    @Test
    public void deveFalharQuandoValorDepositoForMenorOuIgualZero() {
        ContaCorrente contaCorrente = new ContaCorrente();

        boolean depositoZero = contaCorrente.depositar(0);
        boolean depositoNegativo = contaCorrente.depositar(-200);

        Assert.assertFalse(depositoZero);
        Assert.assertFalse(depositoNegativo);
    }

    @Test
    public void deveFalharQuandoTransferenciaForMenorOuIgualZero() {
        ContaCorrente contaCorrente01 = new ContaCorrente();
        ContaCorrente contaCorrente02 = new ContaCorrente();

        boolean transferenciaZero = contaCorrente02.transferir(contaCorrente01, 0);
        boolean transferenciaNegativa = contaCorrente02.transferir(contaCorrente01, -100);

        Assert.assertFalse(transferenciaZero);
        Assert.assertFalse(transferenciaNegativa);
    }
}
