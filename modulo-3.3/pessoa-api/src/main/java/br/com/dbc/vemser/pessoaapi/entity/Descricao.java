package br.com.dbc.vemser.pessoaapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Descricao {
    @Column(name = "descricao")
    private String descricao;
}
