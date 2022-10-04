package br.com.dbc.conta.corrente.conta.interfaces;

import br.com.dbc.conta.corrente.conta.Conta;

public interface Movimentacao {
    abstract boolean sacar(double valor);

    abstract boolean depositar(double valor);

    abstract boolean transferir(Conta contaDestino, double valor);
}
