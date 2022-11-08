package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.dto.RelatorioPersonalizadoDTO;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
    List<PessoaEntity> findByNomeContainsIgnoreCase(String nome);

    PessoaEntity findByCpf(String cpf);

    @Query(" select new br.com.dbc.vemser.pessoaapi.dto.RelatorioPersonalizadoDTO(" +
            " p.idPessoa, " +
            " p.nome, " +
            " p.email, " +
            " c.numero, " +
            " e.cep, " +
            " e.cidade, " +
            " e.estado, " +
            " e.pais, " +
            " pet.nome, " +
            " pf.filme.descricao.descricao, " +
            " pf.descricao.descricao, " +
            " pf.dataAssistido, " +
            " pf.nota, " +
            " pf.filme.nota " +
            ")" +
            " from Pessoa p " +
            " left join p.contatos c " +
            " left join p.enderecos e " +
            " left join p.pet pet " +
            " left join p.pessoaFilme pf " +
            " where (:idPessoa is null or p.idPessoa = :idPessoa)")
    List<RelatorioPersonalizadoDTO> listarRelatorioPersonalizado(Integer idPessoa);
}
