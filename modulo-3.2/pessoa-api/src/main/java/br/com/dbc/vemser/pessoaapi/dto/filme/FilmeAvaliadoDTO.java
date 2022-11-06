package br.com.dbc.vemser.pessoaapi.dto.filme;

import br.com.dbc.vemser.pessoaapi.entity.Descricao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FilmeAvaliadoDTO {
    @NotNull
    @Schema(example = "4")
    private Integer idPessoa;

    @NotNull
    @Schema(example = "3")
    private Integer idFilme;

    @NotNull
    @Schema(example = "2020-01-12")
    private LocalDate dataAssistido;

    @NotNull
    @Schema(example = "Filme teste")
    private Descricao descricao;

    @NotNull
    private Integer nota;
}
