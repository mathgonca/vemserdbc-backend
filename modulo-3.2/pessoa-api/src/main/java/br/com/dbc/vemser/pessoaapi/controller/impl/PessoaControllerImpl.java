package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.controller.PessoaController;
import br.com.dbc.vemser.pessoaapi.dto.*;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoa")
public class PessoaControllerImpl implements PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@Valid @RequestBody PessoaCreateDTO pessoa) {
        return new ResponseEntity<>(pessoaService.cadastrarPessoa(pessoa), HttpStatus.OK);
    }

    @GetMapping
    public List<PessoaDTO> listarPessoa() {
        return pessoaService.listarPessoas();
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable("idPessoa") Integer id,
                                                     @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(pessoaService.atualizarPessoa(id, pessoaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idPessoa}")
    public void deletar(@PathVariable("idPessoa") Integer id) throws RegraDeNegocioException {
        pessoaService.deletarPessoa(id);
    }

    @Operation(summary = "Listar Pessoas com filme")
    @GetMapping("/listar-com-enderecos")
    public List<PessoaEnderecoDTO> listEnderecoPessoa(@RequestParam(required = false) Integer idPessoa)
            throws RegraDeNegocioException {
        return pessoaService.listarEnderecoPessoa(idPessoa);
    }

    @Operation(summary = "Listar Pessoas com Contato")
    @GetMapping("/listar-com-contato")
    public List<PessoaContatoDTO> listContatoPessoa(@RequestParam(required = false) Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listarContatoPessoa(idPessoa);
    }

    @Operation(summary = "Listar Pessoas com Filmes")
    @GetMapping("/listar-com-filme")
    public List<PessoaFilmeDTO> listFilmePessoa(@RequestParam(required = false) Integer idPessoa) throws RegraDeNegocioException {
        return pessoaService.listarFilmesPessoa(idPessoa);
    }

    @Operation(summary = "Listar o Relatório Personalizado")
    @GetMapping("/pessoa-relatorio")
    public List<RelatorioPersonalizadoDTO> findRelatorioPersonalizado(@RequestParam(required = false) Integer idPessoa) {
        return pessoaService.listarRelatorioPersonalizado(idPessoa);
    }
}
