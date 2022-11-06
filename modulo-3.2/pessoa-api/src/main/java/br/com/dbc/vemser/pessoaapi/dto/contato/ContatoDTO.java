package br.com.dbc.vemser.pessoaapi.dto.contato;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContatoDTO extends ContatoCreateDTO {
    @Schema(example = "4")
    private Integer idContato;
}
