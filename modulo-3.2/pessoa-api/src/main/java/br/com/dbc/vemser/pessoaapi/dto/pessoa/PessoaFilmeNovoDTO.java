package br.com.dbc.vemser.pessoaapi.dto.pessoa;

import br.com.dbc.vemser.pessoaapi.entity.Descricao;
import br.com.dbc.vemser.pessoaapi.entity.pk.PessoaFilmeId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilmeNovoDTO {
    @Schema(example = "2000-01-01")
    private LocalDate dataAssistido;

    @Schema(example = "Homem-Aranha")
    private Descricao descricao;

    @Schema(example = "10")
    private Integer nota;

    private PessoaFilmeId pessoaFilmeId;
}
