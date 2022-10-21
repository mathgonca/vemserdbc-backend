package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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
    public ResponseEntity<Contato> cadastrarContato(@PathVariable Integer idPessoa, @Valid @RequestBody Contato contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.cadastrarContato(idPessoa, contato), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Integer id, @RequestBody Contato contatoAtualizado) throws Exception {
        return new ResponseEntity<>(contatoService.atualizarContato(id, contatoAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Integer id) throws Exception {
        contatoService.deletarContato(id);
    }
}
