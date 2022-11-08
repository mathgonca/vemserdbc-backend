package br.com.dbc.vemser.pessoaapi.controller.documentacao;

import br.com.dbc.vemser.pessoaapi.dto.pessoa.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.pessoa.PessoaDTO;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public interface PessoaControllerDoc {

    @Operation(summary = "Cadastrar Pessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cadastra novo Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Pessoa"),
            }
    )
    ResponseEntity<PessoaDTO> cadastrarPessoa(@Valid @RequestBody PessoaCreateDTO pessoa) throws TemplateException, IOException;

    @Operation(summary = "Listar Pessoas", description = "Lista todas as Pessoas do banco.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de Pessoas")
    List<PessoaDTO> listarPessoa();

    @Operation(summary = "Atualizar Pessoa", description = "Atualizar dados de uma Pessoa cadastrada no banco.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados do Pessoa")
            }
    )
    ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable("idPessoa") Integer id,
                                                     @Valid @RequestBody PessoaCreateDTO pessoaAtualizar) throws Exception;

    @Operation(summary = "Deletar Pessoa", description = "Deleta uma Pessoa pelo idPessoa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta Pessoa com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Pessoa não cadastrado")
            }
    )
    void deletar(@PathVariable("idPessoa") Integer id) throws Exception;
}
