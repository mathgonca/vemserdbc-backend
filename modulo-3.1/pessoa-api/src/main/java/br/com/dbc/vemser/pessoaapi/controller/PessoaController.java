package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa) throws Exception {
        return new ResponseEntity<>(pessoaService.cadastrarPessoa(pessoa), HttpStatus.OK) ;
    }

    @GetMapping
    public List<Pessoa> listarPessoa() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/pelo-nome")
    public List<Pessoa> listaPessoaPeloNome(@RequestParam("nome") String nome) {
        return pessoaService.listarPessoaPeloNome(nome);
    }

    @PutMapping("/{idPessoa}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable("idPessoa") Integer id,
                                                  @Valid @RequestBody Pessoa pessoaAtualizar) throws Exception {
        return new ResponseEntity<>(pessoaService.atualizarPessoa(id, pessoaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idPessoa}")
    public void deletar(@PathVariable("idPessoa") Integer id) throws Exception {
        pessoaService.deletarPessoa(id);
    }
}
