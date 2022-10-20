package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/{idEndereco}")
    public Endereco listarEnderecoPeloId(@PathVariable Integer idEndereco) throws Exception {
        return enderecoService.listarEnderecoPeloId(idEndereco);
    }

    @GetMapping("/{idPessoa}/pessoa")
    public List<Endereco> listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa) throws Exception {
        return enderecoService.listarEnderecosPeloIdPessoa(idPessoa);
    }

    @PostMapping("/{idPessoa}")
    public Endereco cadastrarEndereco(@PathVariable Integer idPessoa, @RequestBody Endereco endereco) {
        return  enderecoService.cadastrarEndereco(idPessoa, endereco);
    }

//    PUT “/endereco/{idEndereco}”: altera os dados do endereço.
    @PutMapping("/{idEndereco}")
    public Endereco atualizarEndereco(@PathVariable Integer idEndereco, @RequestBody Endereco endereco) throws Exception {
        return enderecoService.atualizarEndereco(idEndereco, endereco);
    }

//    DELETE “/endereco/{idEndereco}”: remove o endereço pelo id.
    @DeleteMapping("/{idEndereco}")
    public void deletarEndereco(@PathVariable Integer idEndereco) throws Exception {
        enderecoService.deletarEndereco(idEndereco);
    }
}
