package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.Descricao;
import br.com.dbc.vemser.pessoaapi.entity.pk.PessoaFilmeId;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilmeNovoDTO {
    private LocalDate dataAssistido;
    private Descricao descricao;
    private Integer nota;
    private PessoaFilmeId pessoaFilmeId;
}
