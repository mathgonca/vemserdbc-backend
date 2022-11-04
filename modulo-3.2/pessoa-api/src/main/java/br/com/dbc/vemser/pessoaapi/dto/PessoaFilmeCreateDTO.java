package br.com.dbc.vemser.pessoaapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaFilmeCreateDTO {
    private LocalDate dataAssistido;
    private String descricao;
    private int nota;
}
