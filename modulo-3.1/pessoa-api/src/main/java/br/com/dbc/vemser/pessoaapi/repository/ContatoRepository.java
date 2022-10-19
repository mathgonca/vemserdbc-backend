package br.com.dbc.vemser.pessoaapi.repository;


import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ContatoRepository {

    private static List<Contato> listaContatos = new ArrayList<>();

    private AtomicInteger COUNTER = new AtomicInteger();

    public ContatoRepository() {
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, TipoContato.COMERCIAL,
                "11554012421", "Filial centro"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 3, TipoContato.COMERCIAL,
                "11244512505", "Sede"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 1, TipoContato.RESIDENCIAL,
                "11129883781", "Contato familiar"));
        listaContatos.add(new Contato(COUNTER.incrementAndGet(), 2, TipoContato.RESIDENCIAL,
                "11601384705", "Contato principal"));
    }

    public List<Contato> listarContatos() {
        return listaContatos;
    }

    public List<Contato> listarContatoPeloIdPessoa(Integer idPessoa) {
        return listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(idPessoa))
                .toList();
    }

    public Contato cadastrarContato(Integer idPessoa, Contato contato) {
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setIdPessoa(idPessoa);
        listaContatos.add(contato);
        return contato;
    }

    public Contato atualizarContato(Integer id, Contato contatoAtualizado) throws Exception {
        Contato contatoRecuperado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));

        contatoRecuperado.setTipoContato(contatoAtualizado.getTipoContato());
        contatoRecuperado.setNumero(contatoAtualizado.getNumero());
        contatoRecuperado.setDescricao(contatoAtualizado.getDescricao());

        return contatoRecuperado;
    }

    public void deletarContato(Integer id) throws Exception {
        Contato contatoDeletado = listaContatos.stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Contato não encontrado"));

        listaContatos.remove(contatoDeletado);
    }
}
