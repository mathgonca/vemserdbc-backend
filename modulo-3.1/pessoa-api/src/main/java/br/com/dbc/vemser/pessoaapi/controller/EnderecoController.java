package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @Operation(summary = "Listar endereços", description = "Lista todos os Endereços do banco")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Endereços")
    @GetMapping
    public List<EnderecoDTO> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @Operation(summary = "Listar pelo IdEndereco")
    @ApiResponse(responseCode = "200", description = "Retorna lista de Endereços de uma Pessoa filtrado pelo idPessoa")
    @GetMapping("/{idEndereco}")
    public EnderecoDTO listarEnderecoPeloId(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        return enderecoService.listarEnderecoDtoPeloId(idEndereco);
    }

    @Operation(summary = "Listar endereços por Pessoa", description = "Listar todos os Endereços de uma Pessoa filtrada pelo idPessoa.")
    @GetMapping("/{idPessoa}/pessoa")
    public List<EnderecoDTO> listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa) throws RegraDeNegocioException {
        return enderecoService.listarEnderecosPeloIdPessoa(idPessoa);
    }

    @Operation(summary = "Cadastrar Endereço", description = "Cadastrar Endereço de um Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Endereço"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrada")
            }
    )
    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@PathVariable Integer idPessoa,
                                                         @Valid @RequestBody EnderecoCreateDTO endereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.cadastrarEndereco(idPessoa, endereco), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar Endereço", description = "Atualizar dados de um Endereço cadastrado no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Endereço"),
                    @ApiResponse(responseCode = "400", description = "Endereço não cadastrado")
            }
    )
    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Integer idEndereco,
                                                         @Valid @RequestBody EnderecoDTO endereco) throws RegraDeNegocioException {
        return new ResponseEntity<>(enderecoService.atualizarEndereco(idEndereco, endereco), HttpStatus.OK);
    }

    @Operation(summary = "Deletar Endereço pelo Id", description = "Deleta um Endereço pelo idEndereço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Endereço não cadastrado")
            }
    )
    @DeleteMapping("/{idEndereco}")
    public void deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        enderecoService.deletarEndereco(idEndereco);
    }
}
