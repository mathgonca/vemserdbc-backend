package br.com.dbc.vemser.pessoaapi.controller.impl;

import br.com.dbc.vemser.pessoaapi.controller.EnderecoController;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
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
public class EnderecoControllerImpl implements EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/{idEndereco}")
    public EnderecoDTO listarEnderecoPeloId(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        return enderecoService.listarEnderecoDtoPeloId(idEndereco);
    }

    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return enderecoService.listarEnderecoPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@PathVariable Integer idPessoa,
                                                         @Valid @RequestBody EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.cadastrarEndereco(idPessoa, endereco), HttpStatus.OK);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Integer idEndereco,
                                                         @Valid @RequestBody EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.atualizarEndereco(idEndereco, endereco), HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public void deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        enderecoService.deletarEndereco(idEndereco);
    }
}
