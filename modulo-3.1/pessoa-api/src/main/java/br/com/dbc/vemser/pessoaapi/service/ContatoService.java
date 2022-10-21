package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> listarContatos() {
        return contatoRepository.listarContatos();
    }

    public List<Contato> listarContatoPeloIdPessoa(Integer idPessoa) {
        return contatoRepository.listarContatoPeloIdPessoa(idPessoa);
    }

    public Contato cadastrarContato(Integer idPessoa, Contato contato) {
        return contatoRepository.cadastrarContato(idPessoa, contato);
    }

    public Contato atualizarContato(Integer id, Contato contatoAtualizado) throws RegraDeNegocioException {
        Contato contatoRecuperado = contatoRepository.listarContatos().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));

        contatoRecuperado.setTipoContato(contatoAtualizado.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizado.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizado.getDescricao());

        return contatoRecuperado;
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        Contato contatoDeletado = contatoRepository.listarContatos().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));

        contatoRepository.deletarContato(contatoDeletado);
    }
}
