package br.com.dbc.vemser.pessoaapi.service.enums;

public enum TipoEntidade {
    PESSOA("Cadastro"), ENDERECO("Endereço"), CONTATO("Contato");

    TipoEntidade(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
