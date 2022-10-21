package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> listarEnderecos() {
        List<Endereco> enderecoList = enderecoRepository.listarEnderecos();
        return enderecoList.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public Endereco listarEnderecoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        Optional<Endereco> enderecoRecuperado = enderecoRepository.listarEnderecoPeloId(idEndereco);

        if (enderecoRecuperado.isEmpty()) {
            throw new RegraDeNegocioException("Endereço não encontrado");
        }

        return enderecoRecuperado.get();
    }

    public EnderecoDTO listarEnderecoDtoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = listarEnderecoPeloId(idEndereco);
        return objectMapper.convertValue(endereco, EnderecoDTO.class);
    }

    public List<EnderecoDTO> listarEnderecosPeloIdPessoa(Integer idPessoa) throws RegraDeNegocioException {
        pessoaService.listarPessoaPeloId(idPessoa);
        List<Endereco> enderecoList = enderecoRepository.listarEnderecosPeloIdPessoa(idPessoa);
        return enderecoList.stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .toList();
    }

    public EnderecoDTO cadastrarEndereco(Integer idPessoa, EnderecoCreateDTO enderecoDTO) throws RegraDeNegocioException {
        pessoaService.listarPessoaPeloId(idPessoa);

        Endereco endereco = objectMapper.convertValue(enderecoDTO, Endereco.class);
        endereco.setIdPessoa(idPessoa);

        return objectMapper.convertValue(enderecoRepository.cadastrarEndereco(endereco), EnderecoDTO.class);
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Endereco enderecoRecuperado = enderecoRepository.listarEnderecos().stream()
                .filter(endereco -> endereco.getIdEndereco().equals(idEndereco))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Enreço não encontrado"));

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

        return objectMapper.convertValue(enderecoAtualizado, EnderecoDTO.class);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = listarEnderecoPeloId(idEndereco);
        enderecoRepository.deletarEndereco(endereco);
    }
}
