package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;
    private final PessoaRepository pessoaRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> listarEnderecos() {
        return enderecoRepository.findAll().stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
                .toList();
    }

    public EnderecoDTO cadastrarEndereco(Integer idPessoa, EnderecoCreateDTO enderecoDTO) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.listarPessoaPeloId(idPessoa);
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);

        enderecoEntity.getPessoas().add(pessoaEntity);

        EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntity);

        pessoaEntity.getEnderecos().add(enderecoSalvo);
        pessoaRepository.save(pessoaEntity);

        emailService.mandarEmailAcaoCadastro(pessoaEntity.getNome(), pessoaEntity.getEmail(), TipoEntidade.ENDERECO, TipoAcao.CADASTRAR);

        return objectMapper.convertValue(enderecoSalvo, EnderecoDTO.class);
    }

    public EnderecoEntity listarEnderecoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não cadastrado com o Id procurado."));
    }

    public List<EnderecoDTO> listarEnderecoPeloIdPessoa(Integer idPessoa) {
        return enderecoRepository.findAllByPessoasIdPessoa(idPessoa).stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
                .toList();
    }

    public EnderecoDTO listarEnderecoDtoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoEntity endereco = listarEnderecoPeloId(idEndereco);

        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(enderecoCreateDTO.getIdPessoa());

        endereco.setTipo(enderecoCreateDTO.getTipo());
        endereco.setLogradouro(enderecoCreateDTO.getLogradouro());
        endereco.setNumero(enderecoCreateDTO.getNumero());
        endereco.setComplemento(enderecoCreateDTO.getComplemento());
        endereco.setCep(enderecoCreateDTO.getCep());
        endereco.setCidade(enderecoCreateDTO.getCidade());
        endereco.setEstado(enderecoCreateDTO.getEstado());
        endereco.setPais(enderecoCreateDTO.getPais());

        return objectMapper.convertValue(enderecoRepository.save(endereco), EnderecoDTO.class);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        enderecoRepository.delete(enderecoEntity);
    }

}
