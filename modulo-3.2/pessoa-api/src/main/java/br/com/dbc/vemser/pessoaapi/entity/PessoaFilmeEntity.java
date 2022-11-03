package br.com.dbc.vemser.pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pessoa_x_filme")
public class PessoaFilmeEntity {
    @EmbeddedId
    private PessoaFilmeId pessoaFilmeId;

//    @ManyToOne
//    @MapsId("id_filme")
//    private FilmeEntity filme;
//
//    @ManyToOne
//    @MapsId("id_pessoa")
//    private PessoaEntity pessoa;

    @Column(name = "dt_assistido")
    private Date dataAssistido;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nota_pessoa")
    private int nota;
}

