package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.service.PessoaService;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @Operation(summary = "Cadastrar Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Pessoa"),
            }
    )
    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@Valid @RequestBody PessoaCreateDTO pessoa) throws TemplateException, IOException {
        return new ResponseEntity<>(pessoaService.cadastrarPessoa(pessoa), HttpStatus.OK);
    }

    @Operation(summary = "Listar Pessoas", description = "Lista todas as Pessoas do banco.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Pessoas")
    @GetMapping
    public List<PessoaDTO> listarPessoa() {
        return pessoaService.listarPessoas();
    }

    @Operation(summary = "Listar Pessoa pelo nome", description = "Lista todas as pessoas que contém procurado.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Pessoas")
    @GetMapping("/pelo-nome")
    public List<PessoaDTO> listaPessoaPeloNome(@RequestParam("nome") String nome) {
        return pessoaService.listarPessoaPeloNome(nome);
    }

    @Operation(summary = "Atualizar Pessoa", description = "Atualizar dados de uma Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Pessoa")
            }
    )
    @PutMapping("/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable("idPessoa") Integer id,
                                                     @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws Exception {
        return new ResponseEntity<>(pessoaService.atualizarPessoa(id, pessoaAtualizar), HttpStatus.OK);
    }

    @Operation(summary = "Deletar Pessoa", description = "Deleta uma Pessoa pelo idPessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrado")
            }
    )
    @DeleteMapping("/{idPessoa}")
    public void deletar(@PathVariable("idPessoa") Integer id) throws Exception {
        pessoaService.deletarPessoa(id);
    }
}
