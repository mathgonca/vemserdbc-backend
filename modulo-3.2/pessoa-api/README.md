# Pessoa API

## Homework 2

- javax.persistence.EmbeddedId

A tag @EmbeddedId significa que a entidade tem uma chave composta. 
No nosso exemplo *PessoaFilmeEntity* tem uma chave composta *PessoaFilmeId*.

```java
public class PessoaFilmeEntity {
    @EmbeddedId
    private PessoaFilmeId pessoaFilmeId;
}
```

- javax.persistence.Embeddable



- javax.persistence.Embedded