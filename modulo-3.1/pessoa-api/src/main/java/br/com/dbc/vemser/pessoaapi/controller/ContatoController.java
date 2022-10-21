package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
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
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping
    public List<ContatoDTO> listarContatos() {
        return contatoService.listarContatos();
    }

    @GetMapping("/{idPessoa}")
    public List<ContatoDTO> listarContatoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return contatoService.listarContatoPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> cadastrarContato(@PathVariable Integer idPessoa,
                                                       @Valid @RequestBody ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.cadastrarContato(idPessoa, contato), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizarContato(@PathVariable Integer id,
                                                    @Valid @RequestBody ContatoCreateDTO contatoAtualizado) throws Exception {
        return new ResponseEntity<>(contatoService.atualizarContato(id, contatoAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Integer id) throws Exception {
        contatoService.deletarContato(id);
    }
}
