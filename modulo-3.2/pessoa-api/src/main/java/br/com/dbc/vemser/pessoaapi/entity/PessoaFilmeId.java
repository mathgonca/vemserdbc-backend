package br.com.dbc.vemser.pessoaapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class PessoaFilmeId implements Serializable {
    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "id_filme")
    private Integer idFilme;
}
