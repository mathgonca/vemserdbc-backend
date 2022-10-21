package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private ContatoRepository contatoRepository;

    private PessoaService pessoaService;

    public ContatoService(ContatoRepository contatoRepository, PessoaService pessoaService) {
        this.contatoRepository = contatoRepository;
        this.pessoaService = pessoaService;
    }

    public List<Contato> listarContatos() {
        return contatoRepository.listarContatos();
    }

    public List<Contato> listarContatoPeloIdPessoa(Integer idPessoa) {
        return contatoRepository.listarContatoPeloIdPessoa(idPessoa);
    }

    public Contato cadastrarContato(Integer idPessoa, Contato contato) throws RegraDeNegocioException {
         pessoaService.listarPessoaPeloId(idPessoa);
        return contatoRepository.cadastrarContato(idPessoa, contato);
    }

    public Contato atualizarContato(Integer id, Contato contatoAtualizado) throws RegraDeNegocioException {
        Optional<Contato> contatoRecuperado = contatoRepository.listarContatoPeloId(id);

        if(contatoRecuperado.isEmpty()) {
            throw new RegraDeNegocioException("Contato não cadastrado!");
        }

        contatoRecuperado.get().setTipoContato(contatoAtualizado.getTipoContato());
        contatoRecuperado.get().setNumero(contatoAtualizado.getNumero());
        contatoRecuperado.get().setDescricao(contatoAtualizado.getDescricao());

        return contatoRecuperado.get();
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        Optional<Contato> contatoDeletado = contatoRepository.listarContatoPeloId(id);

        if(contatoDeletado.isEmpty()) {
            throw new RegraDeNegocioException("Contato não cadastrado!");
        }

        contatoRepository.deletarContato(contatoDeletado.get());
    }
}
