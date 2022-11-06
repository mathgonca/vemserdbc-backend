package br.com.dbc.vemser.pessoaapi.dto.pessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilmeCreateDTO {
    @Schema(example = "2000-01-01")
    private LocalDate dataAssistido;

    @Schema(example = "O filme começa lento e o fim parece o começo.")
    private String descricao;

    @Schema(example = "5")
    private int nota;
}
