package br.com.dbc.vemser.pessoaapi.entity;

import br.com.dbc.vemser.pessoaapi.entity.enums.TipoPet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_SEQ")
    @SequenceGenerator(name = "PET_SEQ", sequenceName = "seq_pet", allocationSize = 1)
    @Column(name = "id_pet")
    private Integer idPet;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private TipoPet tipoPet;
}
