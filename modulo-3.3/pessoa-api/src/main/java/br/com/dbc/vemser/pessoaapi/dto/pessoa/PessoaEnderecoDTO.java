package br.com.dbc.vemser.pessoaapi.dto.pessoa;

import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PessoaEnderecoDTO {
    @Schema(example = "7")
    private Integer idPessoa;

    @Schema(example = "Lauro Garcia")
    private String nome;

    @Schema(example = "2000-01-01")
    private LocalDate dataNascimento;

    @Schema(example = "lauro_garcia@dbccompany.com.br")
    private String email;

    @Schema(example = "12345678900")
    private String cpf;

    private Set<EnderecoDTO> enderecos;
}
