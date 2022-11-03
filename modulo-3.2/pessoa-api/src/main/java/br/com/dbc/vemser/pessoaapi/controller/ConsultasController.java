package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.ContatoEntity;
import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;

    @GetMapping("/pessoa/nome/{nome}")
    public List<PessoaEntity> retornaPessoaPeloNome(@PathVariable String nome) {
        return pessoaRepository.findByNomeContainsIgnoreCase(nome);
    }

    @GetMapping("/pessoa/cpf/{cpf}")
    public PessoaEntity retornaPessoaPeloCpf(@PathVariable String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @GetMapping("/pessoa")
    public List<PessoaEntity> retornaTodasPessoas() {
        return pessoaRepository.findAll();
    }

    @GetMapping("/contato")
    public List<ContatoEntity> retornaTodosContantos() {
        return contatoRepository.findAll();
    }

    @GetMapping("/endereco")
    public List<EnderecoEntity> retornaTodosEnderecos(){
        return enderecoRepository.findAll();
    }
}
