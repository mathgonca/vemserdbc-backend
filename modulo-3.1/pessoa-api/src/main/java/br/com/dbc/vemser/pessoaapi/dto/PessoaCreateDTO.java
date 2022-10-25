package br.com.dbc.vemser.pessoaapi.dto;

import lombok.Getter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
public class PessoaCreateDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;

    @Past
    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11)
    private String cpf;
    private String email;
}
