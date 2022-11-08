package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class RelatorioPersonalizadoDTO {
    @Schema(example = "4")
    private Integer idPessoa;

    @Schema(example = "Wagner")
    private String nome;

    @Schema(example = "wagner@dbccompany.com.br")
    private String email;

    @Schema(example = "660")
    private String numero;

    @Schema(example = "333444555")
    private String cep;

    @Schema(example = "Canoas")
    private String cidade;

    @Schema(example = "RS")
    private String estado;

    @Schema(example = "Brasil")
    private String pais;

    @Schema(example = "Magal")
    private String petNome;

    @Schema(example = "Mad Max")
    private String descricaoFilme;

    @Schema(example = "Adorei o filme, vai fazer muito sucesso.")
    private String descricaoPessoa;

    @Schema(example = "2000-01-01")
    private LocalDate dataAssistido;

    @Schema(example = "9")
    private Integer notaPessoa;

    @Schema(example = "7")
    private int notaFilme;

}
