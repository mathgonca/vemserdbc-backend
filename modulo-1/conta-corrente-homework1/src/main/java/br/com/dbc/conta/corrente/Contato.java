package br.com.dbc.conta.corrente;

public class Contato {
    final int RESIDENCIAL = 1;
    final int COMERCIAL = 2;
    public String descricao;
    public String telefone;
    public int tipo;

    public void imprimirContato () {
        String tipoDoContatoString = null;

        if (tipo == RESIDENCIAL) {
            tipoDoContatoString = "Residencial";
        }

        if (tipo == COMERCIAL) {
            tipoDoContatoString = "Comercial";
        }

        System.out.println("Tipo " + tipoDoContatoString);
        System.out.println("=".repeat(20));
        System.out.println("Contato: " + telefone);
        System.out.println("Descrição: " + descricao);
    }
}
