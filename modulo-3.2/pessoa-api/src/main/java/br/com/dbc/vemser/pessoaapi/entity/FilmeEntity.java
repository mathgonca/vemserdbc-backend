package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoFilme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "filme")
public class FilmeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILME_SEQ")
    @SequenceGenerator(name = "FILME_SEQ", sequenceName = "seq_filme", allocationSize = 1)
    @Column(name = "id_filme")
    private Integer idFilme;

    @Embedded
    @Column(name = "descricao")
    private Descricao descricao;

    @Column(name = "nota")
    private int nota;

    @Column(name = "tipo")
    private TipoFilme tipoFilme;

    @OneToMany(mappedBy = "filme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PessoaFilmeEntity> pessoaFilmeEntities;
}
