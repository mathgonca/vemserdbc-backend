package br.com.dbc.vemser.pessoaapi.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class CadastroResponseDTO {
    @Schema(example = "1")
    private Integer idUsuario;

    @Schema(example = "user")
    private String login;
}
