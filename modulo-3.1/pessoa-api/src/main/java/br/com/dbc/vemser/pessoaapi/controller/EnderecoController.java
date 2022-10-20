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

    //    GET “/endereco” : recupera todos os endereços.
    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

//    GET “/endereco/{idEndereco}”: recupera o endereço específico.
    @GetMapping("/{idEndereco}")
    public Endereco listarEnderecoPeloId(@PathVariable Integer idEndereco) {
        return null;
    }

//    GET “/endereco/{idPessoa}/pessoa”: recupera os endereços por pessoa.
    @GetMapping("/{idPessoa}/pessoa")
    public Endereco listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return null;
    }

//    POST “/endereco/{idPessoa}”: recebe a pessoa, o endereço e cria o endereço com id da pessoa.
    @PostMapping("/{idPessoa}")
    public Endereco cadatrarEndereco(@PathVariable Integer idPessoa, @RequestBody Endereco endereco) {
        return  enderecoService.cadastrarEndereco(idPessoa, endereco);
    }

//    PUT “/endereco/{idEndereco}”: altera os dados do endereço.
    @PutMapping("/{idEndereco}")
    public Endereco atualizarEndereco(@PathVariable Integer idEndereco, @RequestBody Endereco endereco) {
        return null;
    }

//    DELETE “/endereco/{idEndereco}”: remove o endereço pelo id.
    @DeleteMapping("/{idEndereco}")
    public void deletarEndereco(@PathVariable Integer idEndereco) {

    }
}
