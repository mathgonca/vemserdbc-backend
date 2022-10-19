package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService service;

    public ContatoController() {
        service = new ContatoService();
    }

    @GetMapping
    public List<Contato> listarContatos() {
        return service.listarContatos();
    }

    @GetMapping("/{idPessoa}")
    public List<Contato> listarContatoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return service.listarContatoPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public Contato cadastrarContato(@PathVariable Integer idPessoa, @RequestBody Contato contato) {
        return service.cadastrarContato(idPessoa, contato);
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@PathVariable Integer id, @RequestBody Contato contatoAtualizado) throws Exception {
        return service.atualizarContato(id, contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Integer id) throws Exception {
        service.deletarContato(id);
    }
}
