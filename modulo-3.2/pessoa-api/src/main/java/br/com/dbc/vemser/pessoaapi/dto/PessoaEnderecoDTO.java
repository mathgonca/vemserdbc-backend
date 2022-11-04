package br.com.dbc.vemser.pessoaapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PessoaEnderecoDTO {
    private Integer idPessoa;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String cpf;
    private Set<EnderecoDTO> enderecos;
}
