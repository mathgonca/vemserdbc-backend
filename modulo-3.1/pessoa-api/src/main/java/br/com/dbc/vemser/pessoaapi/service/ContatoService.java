package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;

import java.util.List;

public class ContatoService {

    private ContatoRepository repository;

    public ContatoService() {
        repository = new ContatoRepository();
    }

    public List<Contato> listarContatos() {
        return repository.listarContatos();
    }

    public List<Contato> listarContatoPeloIdPessoa(Integer idPessoa) {
        return repository.listarContatoPeloIdPessoa(idPessoa);
    }

    public Contato cadastrarContato(Integer idPessoa, Contato contato) {
        return repository.cadastrarContato(idPessoa, contato);
    }

    public Contato atualizarContato(Integer id, Contato contatoAtualizado) throws Exception {
        return repository.atualizarContato(id, contatoAtualizado);
    }

    public void deletarContato(Integer id) throws Exception {
        repository.deletarContato(id);
    }
}
