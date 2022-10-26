package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> listarEnderecos() {
        List<Endereco> enderecoList = enderecoRepository.listarEnderecos();
        return enderecoList.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public List<EnderecoDTO> listarEnderecosPeloIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        pessoaService.listarPessoaPeloId(idPessoa);
        List<Endereco> enderecoList = enderecoRepository.listarEnderecosPeloIdPessoa(idPessoa);
        return enderecoList.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public EnderecoDTO cadastrarEndereco(Integer idPessoa, EnderecoCreateDTO enderecoDTO) throws RegraDeNegocioException {
        Pessoa pessoa = pessoaService.listarPessoaPeloId(idPessoa);

        Endereco endereco = objectMapper.convertValue(enderecoDTO, Endereco.class);
        endereco.setIdPessoa(idPessoa);
        Endereco enderecoCadastrado = enderecoRepository.cadastrarEndereco(endereco);

        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.ENDERECO, TipoAcao.CADASTRAR);

        return objectMapper.convertValue(enderecoCadastrado, EnderecoDTO.class);
    }

    public Endereco listarEnderecoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        return enderecoRepository.listarEnderecos().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
    }

    public EnderecoDTO listarEnderecoDtoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = listarEnderecoPeloId(idEndereco);
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = listarEnderecoPeloId(idEndereco);

        enderecoRecuperado.setIdEndereco(idEndereco);
        enderecoRecuperado.setIdPessoa(enderecoCreateDTO.getIdPessoa());
        enderecoRecuperado.setTipo(enderecoCreateDTO.getTipo());
        enderecoRecuperado.setLogradouro(enderecoCreateDTO.getLogradouro());
        enderecoRecuperado.setNumero(enderecoCreateDTO.getNumero());
        enderecoRecuperado.setComplemento(enderecoCreateDTO.getComplemento());
        enderecoRecuperado.setCep(enderecoCreateDTO.getCep());
        enderecoRecuperado.setCidade(enderecoCreateDTO.getCidade());
        enderecoRecuperado.setEstado(enderecoCreateDTO.getEstado());
        enderecoRecuperado.setPais(enderecoCreateDTO.getPais());

        Endereco enderecoAtualizado = listarEnderecoPeloId(idEndereco);

        Pessoa pessoa = pessoaService.listarPessoaPeloId(enderecoAtualizado.getIdPessoa());
        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.ENDERECO, TipoAcao.ATUALIZAR);

        return objectMapper.convertValue(enderecoAtualizado, EnderecoDTO.class);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = listarEnderecoPeloId(idEndereco);
        Pessoa pessoa = pessoaService.listarPessoaPeloId(endereco.getIdPessoa());

        enderecoRepository.deletarEndereco(endereco);

        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.ENDERECO, TipoAcao.DELETAR);
    }
}
