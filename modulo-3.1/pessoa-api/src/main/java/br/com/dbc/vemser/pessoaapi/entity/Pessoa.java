package br.com.dbc.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    private Integer idPessoa;

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
}
