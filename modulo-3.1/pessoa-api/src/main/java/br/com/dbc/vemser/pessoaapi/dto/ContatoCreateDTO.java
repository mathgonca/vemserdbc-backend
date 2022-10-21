package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ContatoCreateDTO {

    private Integer idPessoa;

    @NotNull
    private TipoContato tipoContato;

    @NotNull
    @Size(max = 13)
    private String numero;

    @NotNull
    @NotEmpty
    private String descricao;
}
