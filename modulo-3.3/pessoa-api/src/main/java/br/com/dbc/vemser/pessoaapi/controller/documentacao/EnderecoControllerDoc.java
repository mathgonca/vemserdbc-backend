package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface EnderecoControllerDoc {

    @Operation(summary = "Listar endereços", description = "Lista todos os Endereços do banco")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Endereços")
    List<EnderecoDTO> listarEnderecos();

    @Operation(summary = "Listar pelo IdEndereco")
    @ApiResponse(responseCode = "200", description = "Retorna lista de Endereços de uma Pessoa filtrado pelo idPessoa")
    EnderecoDTO listarEnderecoPeloId(@PathVariable Integer idEndereco) throws RegraDeNegocioException;

    @Operation(summary = "Cadastrar Endereço", description = "Cadastrar Endereço de um Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Endereço"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrada")
            }
    )
    ResponseEntity<EnderecoDTO> cadastrarEndereco(@PathVariable Integer idPessoa,
                                                  @RequestBody @Valid EnderecoCreateDTO endereco) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar Endereço", description = "Atualizar dados de um Endereço cadastrado no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Endereço"),
                    @ApiResponse(responseCode = "400", description = "Endereço não cadastrado")
            }
    )
    ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Integer idEndereco,
                                                  @RequestBody EnderecoCreateDTO endereco) throws RegraDeNegocioException;

    @Operation(summary = "Deletar Endereço pelo Id", description = "Deleta um Endereço pelo idEndereço")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Endereço com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Endereço não cadastrado")
            }
    )
    void deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException;

    @Operation(summary = "Listar endereços por Pessoa", description = "Listar todos os Endereços de uma Pessoa filtrada pelo idPessoa.")
    List<EnderecoDTO> listarEnderecoPeloIdPessoa(@PathVariable Integer idPessoa);
}
