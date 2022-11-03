package br.com.dbc.vemser.pessoaapi.dto;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoFilme;
import lombok.Data;

@Data
public class FilmeCreateDTO {
   private String descricao;

   private int nota;

   private TipoFilme tipoFilme;
}
