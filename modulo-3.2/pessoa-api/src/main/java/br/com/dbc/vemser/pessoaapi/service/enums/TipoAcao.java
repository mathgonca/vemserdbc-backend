package br.com.dbc.vemser.pessoaapi.service.enums;

public enum TipoAcao {
    CADASTRAR("Cadastrado"), ATUALIZAR("Atualizado"), DELETAR("Deletado");

    TipoAcao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
