package br.com.dbc.vemser.pessoaapi.dto.filme;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FilmeDTO extends FilmeCreateDTO {
    @Schema(example = "5")
    private Integer idFilme;
}
