package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
    private PessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaService pessoaService) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.listarEnderecos();
    }

    public Endereco listarEnderecoPeloId(Integer idEndereco) throws Exception {
        Optional<Endereco> enderecoRecuperado = enderecoRepository.listarEnderecoPeloId(idEndereco);

        if(enderecoRecuperado.isEmpty()) {
            throw new Exception("Endereço não encontrado");
        }

        return enderecoRecuperado.get();
    }

    public List<Endereco> listarEnderecosPeloIdPessoa(Integer idPessoa) throws Exception {
        pessoaService.listarPessoaPeloId(idPessoa);
        return enderecoRepository.listarEnderecosPeloIdPessoa(idPessoa);
    }

    public Endereco cadastrarEndereco(Integer idPessoa, Endereco endereco) {
        endereco.setIdPessoa(idPessoa);
        return enderecoRepository.cadastrarEndereco(endereco);
    }

    public Endereco atualizarEndereco(Integer idEndereco, Endereco endereco) throws Exception {
        Endereco enderecoRecuperado = listarEnderecoPeloId(idEndereco);

        enderecoRecuperado.setIdPessoa(endereco.getIdPessoa());
        enderecoRecuperado.setTipo(endereco.getTipo());
        enderecoRecuperado.setLogradouro(endereco.getLogradouro());
        enderecoRecuperado.setNumero(endereco.getNumero());
        enderecoRecuperado.setComplemento(endereco.getComplemento());
        enderecoRecuperado.setCep(endereco.getCep());
        enderecoRecuperado.setCidade(endereco.getCidade());
        enderecoRecuperado.setEstado(endereco.getEstado());
        enderecoRecuperado.setPais(endereco.getPais());

        return enderecoRecuperado;
    }

    public void deletarEndereco(Integer idEndereco) throws Exception {
        Endereco endereco = listarEnderecoPeloId(idEndereco);
        enderecoRepository.deletarEndereco(endereco);
    }
}
