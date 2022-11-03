package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.entity.FilmeEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaFilmeEntity;
import br.com.dbc.vemser.pessoaapi.repository.FilmeRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaFilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeRepository filmeRepository;

    private final PessoaFilmeRepository pessoaFilmeRepository;

    @GetMapping
    private List<FilmeEntity> listarFilmes() {
        return filmeRepository.findAll();
    }

    @GetMapping("/pessoa-filme")
    private List<PessoaFilmeEntity> listarPessoaFilme() {
        return pessoaFilmeRepository.findAll();
    }
}
