package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class EnderecoCreateDTO {

    private Integer idPessoa;

    @NotNull
    @Schema(description = "Tipo de Endereço", example = "RESIDENCIAL ou COMERCIAL")
    private TipoEndereco tipo;

    @NotEmpty
    @Size(max = 250)
    @Schema(example = "Av. Felipe Camarão")
    private String logradouro;

    @NotNull
    @Schema(example = "312")
    private Integer numero;

    @Schema(required = false, example = "123C")
    private String complemento;

    @NotEmpty
    @NotNull
    @Size(max = 8)
    @Schema(example = "999333123")
    private String cep;

    @NotEmpty
    @NotNull
    @Size(max = 250)
    @Schema(example = "Canoas")
    private String cidade;

    @NotNull
    @Schema(example = "Rio Grande do Sul")
    private String estado;

    @NotNull
    @Schema(example = "Brasil")
    private String pais;
}
