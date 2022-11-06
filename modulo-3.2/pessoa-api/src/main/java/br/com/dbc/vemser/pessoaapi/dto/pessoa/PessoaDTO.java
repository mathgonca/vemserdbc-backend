package br.com.dbc.vemser.pessoaapi.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PessoaDTO extends PessoaCreateDTO {
    @Schema(example = "6")
    private Integer idPessoa;
}
