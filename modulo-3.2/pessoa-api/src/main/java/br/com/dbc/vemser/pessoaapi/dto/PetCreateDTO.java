package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoPet;
import lombok.Data;

@Data
public class PetCreateDTO {
    private Integer idPessoa;
    private String nome;
    private TipoPet tipoPet;
}
