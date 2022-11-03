package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.PessoaFilmeEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaFilmeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFilmeRepository extends JpaRepository<PessoaFilmeEntity, PessoaFilmeId> {
}
