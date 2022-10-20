package br.com.dbc.vemser.pessoaapi.repository;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.TipoEndereco;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EnderecoRepository {

    private static List<Endereco> listaEnderecos = new ArrayList<>();

    private AtomicInteger COUNTER = new AtomicInteger();

    public EnderecoRepository() {
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 3, TipoEndereco.COMERCIAL,
                "Rua Sete", 123, null, "92314060", "Canoas", "RS",
                "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 1, TipoEndereco.COMERCIAL,
                "Quadra J Dois", 123, null, "92440166", "Canoas", "RS",
                "Brasil"));
        listaEnderecos.add(new Endereco(COUNTER.incrementAndGet(), 2, TipoEndereco.RESIDENCIAL,
                "Rua G", 123, null, "92412085", "Canoas", "RS",
                "Brasil"));
    }

    public List<Endereco> listarEnderecos() {
        return listaEnderecos;
    }


    public Optional<Endereco> listarEnderecoPeloId(Integer idEndereco) {
        Optional<Endereco> enderecoRecuperado = listaEnderecos.stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst();

        return enderecoRecuperado;
    }

    public List<Endereco> listarEnderecosPeloIdPessoa(Integer idPessoa) {
        return listaEnderecos.stream()
                .filter(endereco -> endereco.getIdPessoa().equals(idPessoa))
                .toList();
    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        endereco.setIdEndereco(COUNTER.incrementAndGet());
        listaEnderecos.add(endereco);
        return endereco;
    }

    public void deletarEndereco(Endereco endereco) {
        listaEnderecos.remove(endereco);
    }
}
