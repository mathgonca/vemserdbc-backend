package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.controller.PessoaController;
import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
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
}
