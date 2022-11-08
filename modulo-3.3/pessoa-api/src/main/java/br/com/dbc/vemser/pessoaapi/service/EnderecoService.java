package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.pessoa.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.EnderecoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.EnderecoRepository;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
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
    private final PessoaRepository pessoaRepository;
    //    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    private EnderecoDTO setEnderecoDTO(EnderecoEntity endereco) {
        EnderecoDTO enderecoDTO = objectMapper.convertValue(endereco, EnderecoDTO.class);
        Integer idPessoa;

        Optional<PessoaEntity> pessoa = endereco.getPessoas().stream().findFirst();
        if (pessoa.isEmpty()) {
            idPessoa = null;
        } else {
            idPessoa = pessoa.get().getIdPessoa();
        }

        enderecoDTO.setIdPessoa(idPessoa);
        return enderecoDTO;
    }

    public List<EnderecoDTO> listarEnderecos() {
        return enderecoRepository.findAll().stream()
                .map(this::setEnderecoDTO)
                .toList();
    }

    public EnderecoDTO cadastrarEndereco(Integer idPessoa, EnderecoCreateDTO enderecoDTO) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.listarPessoaPeloId(idPessoa);
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoDTO, EnderecoEntity.class);

        enderecoEntity.getPessoas().add(pessoaEntity);

        EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntity);

        pessoaEntity.getEnderecos().add(enderecoSalvo);
        pessoaRepository.save(pessoaEntity);

//        emailService.mandarEmailAcaoCadastro(pessoaEntity.getNome(), pessoaEntity.getEmail(),
//                TipoEntidade.ENDERECO, TipoAcao.CADASTRAR);

        return objectMapper.convertValue(enderecoSalvo, EnderecoDTO.class);
    }

    public EnderecoEntity listarEnderecoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não cadastrado com o Id procurado."));
    }

    public List<EnderecoDTO> listarEnderecoPeloIdPessoa(Integer idPessoa) {
        return enderecoRepository.findAllByPessoasIdPessoa(idPessoa).stream()
                .map(this::setEnderecoDTO)
                .toList();
    }

    public EnderecoDTO listarEnderecoDtoPeloId(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        return setEnderecoDTO(enderecoEntity);
    }

    public EnderecoDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoEntity endereco = listarEnderecoPeloId(idEndereco);

        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(enderecoCreateDTO.getIdPessoa());

        endereco.getPessoas().clear();
        endereco.getPessoas().add(pessoa);
        endereco.setTipo(enderecoCreateDTO.getTipo());
        endereco.setLogradouro(enderecoCreateDTO.getLogradouro());
        endereco.setNumero(enderecoCreateDTO.getNumero());
        endereco.setComplemento(enderecoCreateDTO.getComplemento());
        endereco.setCep(enderecoCreateDTO.getCep());
        endereco.setCidade(enderecoCreateDTO.getCidade());
        endereco.setEstado(enderecoCreateDTO.getEstado());
        endereco.setPais(enderecoCreateDTO.getPais());
        
        EnderecoEntity enderecoSalvo = enderecoRepository.save(endereco);

        return setEnderecoDTO(enderecoSalvo);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = listarEnderecoPeloId(idEndereco);
        enderecoRepository.delete(enderecoEntity);
    }
}
