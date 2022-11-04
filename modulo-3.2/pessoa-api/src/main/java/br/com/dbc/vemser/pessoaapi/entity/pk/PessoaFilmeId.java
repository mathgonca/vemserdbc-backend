package br.com.dbc.vemser.pessoaapi.entity.pk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PessoaFilmeId implements Serializable {
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "id_filme")
    private Integer idFilme;
}
