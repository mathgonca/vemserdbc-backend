package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public PessoaDTO cadastrarPessoa(PessoaCreateDTO pessoaCreateDTO) {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);

        PessoaDTO pessoaCadastrada = objectMapper.convertValue(pessoaRepository.save(pessoaEntity), PessoaDTO.class);
        emailService.sendEmailCadastroPessoa(pessoaCadastrada.getNome(), pessoaCadastrada.getIdPessoa(), pessoaCadastrada.getEmail());
        return pessoaCadastrada;
    }

    public List<PessoaDTO> listarPessoas() {
        List<PessoaEntity> pessoaEntityList = pessoaRepository.findAll();

        return pessoaEntityList.stream()
                .map(pessoaEntity -> objectMapper.convertValue(pessoaEntity, PessoaDTO.class))
                .toList();
    }

    public PessoaEntity listarPessoaPeloId(Integer idPessoa) throws RegraDeNegocioException {
        Optional<PessoaEntity> pessoaRecuperada = pessoaRepository.findById(idPessoa);

        if (pessoaRecuperada.isEmpty()) {
            throw new RegraDeNegocioException("Pessoa não cadastrada");
        }

        return pessoaRecuperada.get();
    }

    public PessoaDTO atualizarPessoa(Integer id, PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = listarPessoaPeloId(id);

        pessoaEntityRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaEntityRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaEntityRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        pessoaEntityRecuperada.setEmail(pessoaAtualizar.getEmail());

        PessoaEntity pessoaEntityAtualizada = pessoaRepository.save(pessoaEntityRecuperada);

        emailService.mandarEmailAcaoCadastro(pessoaAtualizar.getNome(), pessoaAtualizar.getEmail(),
                TipoEntidade.PESSOA, TipoAcao.ATUALIZAR);

        return objectMapper.convertValue(pessoaEntityAtualizada, PessoaDTO.class);
    }

    public void deletarPessoa(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = listarPessoaPeloId(id);
        pessoaRepository.delete(pessoaEntityRecuperada);

        emailService.mandarEmailAcaoCadastro(pessoaEntityRecuperada.getNome(), pessoaEntityRecuperada.getEmail(),
                TipoEntidade.PESSOA, TipoAcao.DELETAR);
    }
}
