package br.com.dbc.vemser.pessoaapi.dto.pet;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetDTO extends PetCreateDTO {
    @Schema(example = "3")
    private Integer idPet;

    @Schema(example = "7")
    private Integer idPessoa;
}
