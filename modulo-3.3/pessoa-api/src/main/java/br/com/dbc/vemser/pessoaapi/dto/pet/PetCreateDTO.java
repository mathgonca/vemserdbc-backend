package br.com.dbc.vemser.pessoaapi.dto.pet;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoPet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetCreateDTO {
    @Schema(example = "Pucca")
    private String nome;

    @Schema(example = "CACHORRO, GATO, GAXINIM")
    private TipoPet tipoPet;
}
