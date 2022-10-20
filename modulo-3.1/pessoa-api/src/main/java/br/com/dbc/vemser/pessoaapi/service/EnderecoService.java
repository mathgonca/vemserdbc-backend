package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.listarEnderecos();
    }

    public Endereco cadastrarEndereco(Integer idPessoa, Endereco endereco) {
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.cadastrarEndereco(endereco);
    }
}
