package br.com.dbc.vemser.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(example = "Fernando")
    private String nome;

    @Past
    @NotNull
    @Schema(example = "2000-02-18")
    private LocalDate dataNascimento;

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11)
    @Schema(minLength = 11, maxLength = 11, example = "12345678900")
    private String cpf;

    @NotNull
    @NotEmpty
    @Schema(example = "seu@email.com.br")
    private String email;
}
