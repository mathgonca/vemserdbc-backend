package br.com.dbc.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    private Integer idContato;
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
