package br.com.dbc.conta.corrente.cliente;

public class Contato {
    private final int RESIDENCIAL = 1;
    private final int COMERCIAL = 2;

    private String descricao;
    private String telefone;
    private int tipo;

    public Contato() {
    }

    public Contato(String descricao, String telefone, int tipo) {
        this.descricao = descricao;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public int getRESIDENCIAL() {
        return RESIDENCIAL;
    }

    public int getCOMERCIAL() {
        return COMERCIAL;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void imprimirContato() {
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
