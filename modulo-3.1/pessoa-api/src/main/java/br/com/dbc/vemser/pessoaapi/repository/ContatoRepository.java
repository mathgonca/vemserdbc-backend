package br.com.dbc.vemser.pessoaapi.repository;


import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.TipoContato;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
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
                .filter(contato -> contato.getIdPessoa().equals(idPessoa))
                .toList();
    }

    public Contato cadastrarContato(Integer idPessoa, Contato contato) {
        contato.setIdContato(COUNTER.incrementAndGet());
        contato.setIdPessoa(idPessoa);
        listaContatos.add(contato);

        return contato;
    }

    public void deletarContato(Contato contato) {
        listaContatos.remove(contato);
    }
}
