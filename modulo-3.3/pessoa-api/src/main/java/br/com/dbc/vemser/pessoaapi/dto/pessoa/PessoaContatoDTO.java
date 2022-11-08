package br.com.dbc.vemser.pessoaapi.dto.pessoa;

import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class PessoaContatoDTO {
    @Schema(example = "7")
    private Integer idPessoa;

    @Schema(example = "Lauro Garcia")
    private String nome;

    @Schema(example = "lauro_garcia@dbccompany.com.br")
    private String email;

    @Schema(example = "12345678900")
    private String cpf;

    private Set<ContatoDTO> contatos;
}

