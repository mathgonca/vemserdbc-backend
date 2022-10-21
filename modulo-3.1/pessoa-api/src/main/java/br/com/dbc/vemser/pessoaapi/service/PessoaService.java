package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.cadastrarPessoa(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    public Pessoa atualizarPessoa(Integer id, Pessoa pessoaAtualizar) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = pessoaRepository.listarPessoas().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));

        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());

        return pessoaRecuperada;
    }

    public void deletarPessoa(Integer id) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = listarPessoas().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));

        pessoaRepository.deletarPessoa(pessoaRecuperada);
    }

    public List<Pessoa> listarPessoaPeloNome(String nome) {
        return pessoaRepository.listByName(nome);
    }

    public Pessoa listarPessoaPeloId(Integer idPessoa) throws RegraDeNegocioException {
        Optional<Pessoa> pessoaRecuperada = pessoaRepository.listarPessoaPeloId(idPessoa);

        if (pessoaRecuperada.isEmpty()){
            throw new RegraDeNegocioException("Pessoa não cadastrada");
        }

        return pessoaRecuperada.get();
    }
}
