package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.pk.PessoaFilmeId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pessoa_x_filme")
public class PessoaFilmeEntity {
    @EmbeddedId
    private PessoaFilmeId pessoaFilmeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idFilme")
    @JoinColumn(name = "id_filme")
    private FilmeEntity filme;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private PessoaEntity pessoa;

    @Column(name = "dt_assistido")
    private LocalDate dataAssistido;

    @Column(name = "descricao")
    private Descricao descricao;

    @Column(name = "nota_pessoa")
    private int nota;
}

