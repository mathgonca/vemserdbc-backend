package br.com.dbc.vemser.pessoaapi.controller.feignclient;

import br.com.dbc.vemser.pessoaapi.client.DadosPessoaisClient;
import br.com.dbc.vemser.pessoaapi.dto.DadosPessoaisDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {

    private final DadosPessoaisClient service;

    @GetMapping
    public List<DadosPessoaisDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DadosPessoaisDTO post(@RequestBody DadosPessoaisDTO dadosPessoaisDTO) {
        return service.post(dadosPessoaisDTO);
    }

    @PutMapping("/{cpf}")
    public DadosPessoaisDTO put(@PathVariable String cpf, @RequestBody DadosPessoaisDTO dadosPessoaisDTO) {
        return service.put(cpf, dadosPessoaisDTO);
    }

    @DeleteMapping("/{cpf}")
    public void delete(@PathVariable String cpf) {
        service.delete(cpf);
    }

    @GetMapping("/{cpf}")
    public DadosPessoaisDTO get(@PathVariable String cpf) {
        return service.get(cpf);
    }
}
