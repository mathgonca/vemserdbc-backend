package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ContatoCreateDTO {

    private Integer idPessoa;

    @NotNull
    @Schema(example = "RESIDENCIAL ou COMERCIAL")
    private TipoContato tipoContato;

    @NotNull
    @Size(max = 13)
    @Schema(example = "123")
    private String numero;

    @NotNull
    @NotEmpty
    @Schema(example = "Ao lado da padaria Padoca")
    private String descricao;
}
