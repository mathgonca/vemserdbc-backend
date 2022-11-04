package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.dto.FilmeAvaliadoDTO;
import br.com.dbc.vemser.pessoaapi.dto.FilmeCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.FilmeDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.FilmeService;
import br.com.dbc.vemser.pessoaapi.service.PessoaFilmeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/filme")
public class FilmeController {
    private final FilmeService filmeService;
    private final PessoaFilmeService pessoaFilmeService;

    @Operation(summary = "Listar todos os filmes")
    @GetMapping
    public List<FilmeDTO> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @Operation(summary = "Listar filme pelo Id")
    @GetMapping("/{idFilme}")
    public FilmeDTO listarFilmePeloId(@PathVariable Integer idFilme) throws RegraDeNegocioException {
        return filmeService.listarFilmeDTOPeloId(idFilme);
    }

    @Operation(summary = "Cadastrar Filme")
    @PostMapping
    public FilmeDTO cadastrarFilme(@RequestBody FilmeCreateDTO filme) {
        return filmeService.cadastrarFilme(filme);
    }

    @Operation(summary = "Avaliar filme")
    @PutMapping("/avaliar-filme/{idPessoa}")
    public ResponseEntity<FilmeAvaliadoDTO> avaliarFilme(@PathVariable Integer idPessoa,
                                                         @Valid @RequestBody FilmeAvaliadoDTO avaliarfilmeAvaliadoDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaFilmeService.avaliarFilme(idPessoa, avaliarfilmeAvaliadoDTO), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar Filme")
    @PutMapping("/{idFilme}")
    public ResponseEntity<FilmeDTO> atualizarFilme(@PathVariable Integer idFilme,
                                                   @Valid @RequestBody FilmeCreateDTO filmeCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(filmeService.atualizarFilme(idFilme, filmeCreateDTO), HttpStatus.OK);
    }

    @Operation(summary = "Deletar Filme")
    @DeleteMapping("/{idFilme}")
    public void deletarFilme(@PathVariable Integer idFilme) throws RegraDeNegocioException {
        filmeService.deletarFilme(idFilme);
    }
}
