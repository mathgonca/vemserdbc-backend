package br.com.dbc.vemser.pessoaapi.dto.contato;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ContatoUpdateDTO {
    @Schema(example = "6")
    private Integer idPessoa;

    @NotNull
    @Schema(example = "RESIDENCIAL ou COMERCIAL")
    private TipoContato tipoContato;

    @NotNull
    @Size(max = 13)
    @Schema(example = "98123555443")
    private String numero;

    @NotNull
    @NotEmpty
    @Schema(example = "Telefone da casa da praia")
    private String descricao;
}
