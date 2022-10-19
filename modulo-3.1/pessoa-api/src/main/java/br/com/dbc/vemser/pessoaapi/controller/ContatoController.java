package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public List<Contato> listarContatos() {
        return contatoService.listarContatos();
    }

    @GetMapping("/{idPessoa}")
    public List<Contato> listarContatoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return contatoService.listarContatoPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public Contato cadastrarContato(@PathVariable Integer idPessoa, @RequestBody Contato contato) {
        return contatoService.cadastrarContato(idPessoa, contato);
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@PathVariable Integer id, @RequestBody Contato contatoAtualizado) throws Exception {
        return contatoService.atualizarContato(id, contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Integer id) throws Exception {
        contatoService.deletarContato(id);
    }
}
