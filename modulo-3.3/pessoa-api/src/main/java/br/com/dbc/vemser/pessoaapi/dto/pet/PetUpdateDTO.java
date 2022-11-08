package br.com.dbc.vemser.pessoaapi.dto.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetUpdateDTO extends PetCreateDTO {
    @Schema(example = "7")
    private Integer idPessoa;
}
