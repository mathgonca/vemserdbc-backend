package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

    List<EnderecoEntity> findAllByPessoasIdPessoa(Integer idPessoa);

    @Query("select e from endereco_pessoa e where e.pais=?1")
    List<EnderecoEntity> listarEnderecoPeloPais(String nome);
}
