package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
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
        EnderecoEntity enderecoEntityCadastrado = enderecoRepository.save(enderecoEntity);

        emailService.mandarEmailAcaoCadastro(pessoaEntity.getNome(), pessoaEntity.getEmail(), TipoEntidade.ENDERECO, TipoAcao.CADASTRAR);

        return objectMapper.convertValue(enderecoEntityCadastrado, EnderecoDTO.class);
    }

    public EnderecoEntity listarEnderecoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        Optional<EnderecoEntity> endereco = enderecoRepository.findById(idEndereco);

        if (endereco.isEmpty()) {
            throw new RegraDeNegocioException("Endereço não cadastrado!");
        }

        return endereco.get();
    }

    public EnderecoDTO listarEnderecoDtoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntityRecuperado = listarEnderecoPeloId(idEndereco);

        enderecoEntityRecuperado.setIdEndereco(idEndereco);
        enderecoEntityRecuperado.setTipo(enderecoCreateDTO.getTipo());
        enderecoEntityRecuperado.setLogradouro(enderecoCreateDTO.getLogradouro());
        enderecoEntityRecuperado.setNumero(enderecoCreateDTO.getNumero());
        enderecoEntityRecuperado.setComplemento(enderecoCreateDTO.getComplemento());
        enderecoEntityRecuperado.setCep(enderecoCreateDTO.getCep());
        enderecoEntityRecuperado.setCidade(enderecoCreateDTO.getCidade());
        enderecoEntityRecuperado.setEstado(enderecoCreateDTO.getEstado());
        enderecoEntityRecuperado.setPais(enderecoCreateDTO.getPais());

        EnderecoEntity enderecoEntityAtualizado = listarEnderecoPeloId(idEndereco);

        return objectMapper.convertValue(enderecoEntityAtualizado, EnderecoDTO.class);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        enderecoRepository.delete(enderecoEntity);
    }
}
