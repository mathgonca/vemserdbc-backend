package br.com.dbc.vemser.pessoaapi.dto.filme;

import br.com.dbc.vemser.pessoaapi.entity.Descricao;
import br.com.dbc.vemser.pessoaapi.entity.enums.TipoFilme;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FilmeCreateDTO {
   @NotNull
   @NotEmpty
   @Schema(example = "Muito bom")
   private Descricao descricao;

   @NotNull
   @NotEmpty
   @Schema(example = "7")
   private int nota;

   @NotNull
   @NotEmpty
   @Schema(example = "ACAO, TERROR, SUSPENSE, DRAMA, COMEDIA")
   private TipoFilme tipoFilme;
}
