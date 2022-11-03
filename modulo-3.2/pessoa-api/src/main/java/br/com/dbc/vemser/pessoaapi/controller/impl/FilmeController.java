package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.dto.FilmeCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.FilmeDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService filmeService;

    @GetMapping
    public List<FilmeDTO> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @GetMapping("/{idFilme}")
    public FilmeDTO listarFilmePeloId(@PathVariable Integer idFilme) throws RegraDeNegocioException {
        return filmeService.listarFilmeDTOPeloId(idFilme);
    }

    @PostMapping
    public FilmeDTO cadastrarFilme(@RequestBody FilmeCreateDTO filme) {
        return filmeService.cadastrarFilme(filme);
    }

    @PutMapping("/{idFilme}")
    public FilmeDTO atualizarFilme(@PathVariable Integer idFilme, @RequestBody FilmeCreateDTO filmeCreateDTO) throws RegraDeNegocioException {
        return filmeService.atualizarFilme(idFilme, filmeCreateDTO);
    }

    @DeleteMapping("/{idFilme}")
    public void deletarFilme(@PathVariable Integer idFilme) throws RegraDeNegocioException {
        filmeService.deletarFilme(idFilme);
    }
}
