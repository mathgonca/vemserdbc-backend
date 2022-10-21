package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/{idEndereco}")
    public Endereco listarEnderecoPeloId(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        return enderecoService.listarEnderecoPeloId(idEndereco);
    }

    @GetMapping("/{idPessoa}/pessoa")
    public List<Endereco> listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa) throws RegraDeNegocioException {
        return enderecoService.listarEnderecosPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable Integer idPessoa,
                                                      @Valid @RequestBody Endereco endereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.cadastrarEndereco(idPessoa, endereco), HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer idEndereco,
                                                      @Valid @RequestBody Endereco endereco) throws Exception {
        return new ResponseEntity<>(enderecoService.atualizarEndereco(idEndereco, endereco), HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public void deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        enderecoService.deletarEndereco(idEndereco);
    }
}
