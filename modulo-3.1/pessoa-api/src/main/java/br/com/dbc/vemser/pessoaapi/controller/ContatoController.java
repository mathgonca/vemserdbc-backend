package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar Contatos", description = "Listar todos os Contatos do banco.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Contatos")
    @GetMapping
    public List<ContatoDTO> listarContatos() {
        return contatoService.listarContatos();
    }

    @Operation(summary = "Listar Contatos por Pessoa", description = "Listar todos os Contatos de uma Pessoa filtrado pelo idPessoa.")
    @ApiResponse(responseCode = "200", description = "Retorna lista de Contatos da Pessoa")
    @GetMapping("/{idPessoa}")
    public List<ContatoDTO> listarContatoPeloIdPessoa(@PathVariable Integer idPessoa) {
        return contatoService.listarContatoPeloIdPessoa(idPessoa);
    }

    @Operation(summary = "Cadastrar Contato", description = "Cadastrar Contato de um Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Contato"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrada")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> cadastrarContato(@PathVariable Integer idPessoa,
                                                       @Valid @RequestBody ContatoCreateDTO contato) throws RegraDeNegocioException {
        return new ResponseEntity<>(contatoService.cadastrarContato(idPessoa, contato), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar Contato", description = "Atualizar dados de um Contato cadastrado no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Contato"),
                    @ApiResponse(responseCode = "400", description = "Contato não cadastrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizarContato(@PathVariable Integer id,
                                                       @Valid @RequestBody ContatoCreateDTO contatoAtualizado) throws Exception {
        return new ResponseEntity<>(contatoService.atualizarContato(id, contatoAtualizado), HttpStatus.OK);
    }

    @Operation(summary = "Deletar Contato pelo Id", description = "Deleta um Contato pelo idContato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Contato não cadastrado")
            }
    )
    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable Integer id) throws Exception {
        contatoService.deletarContato(id);
    }
}
