package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) throws Exception {
        return pessoaService.cadastrarPessoa(pessoa);
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
    public Pessoa atualizarPessoa(@PathVariable("idPessoa") Integer id,
                                  @RequestBody Pessoa pessoaAtualizar) throws Exception {
        return pessoaService.atualizarPessoa(id, pessoaAtualizar);
    }

    @DeleteMapping("/{idPessoa}") // localhost:8080/pessoa/10
    public void deletar(@PathVariable("idPessoa") Integer id) throws Exception {
        pessoaService.deletarPessoa(id);
    }
}
