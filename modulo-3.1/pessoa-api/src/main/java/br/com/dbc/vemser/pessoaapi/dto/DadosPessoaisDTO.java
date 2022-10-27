package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class DadosPessoaisDTO {
    @Schema(example = "Marina Oliveira")
    private String nome;

    @Schema(example = "12345678912")
    private String cpf;

    @Schema(example = "5645656")
    private String rg;

    @Schema(example = "564569888")
    private String cnh;

    @Schema(example = "Joana Oliveira da Silva")
    private String nomeMae;

    @Schema(example = "Paulo Pedro Oliveira")
    private String nomePai;

    @Schema(example = "Paulo Pedro Oliveira")
    private String tituloEleitor;

    @Schema(example = "F ou M")
    private Sexo sexo;
}