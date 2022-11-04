package br.com.dbc.vemser.pessoaapi.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PessoaContatoDTO {
    private Integer idPessoa;
    private String nome;
    private String email;
    private String cpf;
    private Set<ContatoDTO> contatos;
}

