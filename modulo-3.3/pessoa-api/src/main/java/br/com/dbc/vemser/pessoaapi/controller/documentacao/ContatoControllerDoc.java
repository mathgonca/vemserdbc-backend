package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoUpdateDTO;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ContatoControllerDoc {
    @Operation(summary = "Listar Contatos", description = "Listar todos os Contatos do banco.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Contatos")
    List<ContatoDTO> listarContatos();

    @Operation(summary = "Cadastrar Contato", description = "Cadastrar Contato de um Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Contato"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrada")
            }
    )
    ResponseEntity<ContatoDTO> cadastrarContato(@PathVariable Integer idPessoa,
                                                @Valid @RequestBody ContatoCreateDTO contato) throws RegraDeNegocioException;

    @Operation(summary = "Atualizar Contato", description = "Atualizar dados de um Contato cadastrado no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Contato"),
                    @ApiResponse(responseCode = "400", description = "Contato não cadastrado")
            }
    )
    ResponseEntity<ContatoDTO> atualizarContato(@PathVariable Integer id,
                                                @Valid @RequestBody ContatoUpdateDTO contatoAtualizado) throws RegraDeNegocioException;

    @Operation(summary = "Deletar Contato pelo Id", description = "Deleta um Contato pelo idContato")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Contato com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Contato não cadastrado")
            }
    )
    void deletarContato(@PathVariable Integer id) throws RegraDeNegocioException;
}