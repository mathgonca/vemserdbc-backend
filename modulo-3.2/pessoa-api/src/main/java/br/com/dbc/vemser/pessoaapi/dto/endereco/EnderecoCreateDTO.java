package br.com.dbc.vemser.pessoaapi.dto.endereco;

import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.entity.enums.TipoEndereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCreateDTO {
    @NotNull
    @Schema(description = "Tipo de Endereço", example = "RESIDENCIAL ou COMERCIAL")
    private TipoEndereco tipo;

    private Integer idPessoa;

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
    @Schema(example = "99933323")
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
