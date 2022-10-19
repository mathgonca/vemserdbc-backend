package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) throws Exception {
        return pessoaRepository.cadastrarPessoa(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.listarPessoas();
    }

    public Pessoa atualizarPessoa(Integer id, Pessoa pessoaAtualizar) throws Exception {
        Pessoa pessoaRecuperada = pessoaRepository.listarPessoas().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não econtrada"));

        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());

        return pessoaRecuperada;
    }

    public void deletarPessoa(Integer id) throws Exception {
        Pessoa pessoaRecuperada = listarPessoas().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Pessoa não econtrada"));

        pessoaRepository.deletarPessoa(pessoaRecuperada);
    }

    public List<Pessoa> listarPessoaPeloNome(String nome) {
        return pessoaRepository.listByName(nome);
    }
}
