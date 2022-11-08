# Pessoa API

## Homework 2

- javax.persistence.EmbeddedId

A anotação @EmbeddedId significa que a entidade tem uma chave composta.
No nosso exemplo *PessoaFilmeEntity* tem uma chave composta *PessoaFilmeId*.

```java
public class PessoaFilmeEntity {
    @EmbeddedId
    private PessoaFilmeId pessoaFilmeId;
}
```

- javax.persistence.Embeddable

A anotação @Embeddable serve para sinalizar que a classe está disponivel para ser utiliza com uma chave estrangeira
composta.
No nosso exemplo *PessoaFilmeId* é uma chave composta.

```java

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
```

- javax.persistence.Embedded

A Anotação JPA @Embedded é usada para inbutir um tipo dentro de uma entididade. 
No nosso exemplo, na classe *PessoaFilmeId*, a propriedade *descricao* é um objeto inbutível com o mesmo nome para efeito
de demonstração.

```java
@Embedded
@Column(name = "descricao")
private Descricao descricao;
```

```java
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Descricao {
    @Column(name = "descricao")
    private String descricao;
}
```

### Referências

- Baeldung: [Spring JPA @Embedded and @EmbeddedId](https://www.baeldung.com/spring-jpa-embedded-method-parameters)
- Baeldung: [JPA @Embedded And @Embeddable](https://www.baeldung.com/jpa-embedded-embeddable)