package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.RelatorioPersonalizadoDTO;
import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.endereco.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.pessoa.*;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
//    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public PessoaDTO cadastrarPessoa(PessoaCreateDTO pessoaCreateDTO) {
        PessoaEntity pessoaEntity = objectMapper.convertValue(pessoaCreateDTO, PessoaEntity.class);

        PessoaDTO pessoaCadastrada = objectMapper.convertValue(pessoaRepository.save(pessoaEntity), PessoaDTO.class);

        String nomePessoaCadastrada = pessoaCadastrada.getNome();
        Integer idPessoa = pessoaCadastrada.getIdPessoa();
        String email = pessoaCadastrada.getEmail();
//        emailService.sendEmailCadastroPessoa(nomePessoaCadastrada, idPessoa, email);

        return pessoaCadastrada;
    }

    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .toList();
    }

    public PessoaEntity listarPessoaPeloId(Integer idPessoa) throws RegraDeNegocioException {
        return pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não cadastrada"));
    }

    public PessoaDTO atualizarPessoa(Integer id, PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = listarPessoaPeloId(id);

        pessoaEntityRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaEntityRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaEntityRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());
        pessoaEntityRecuperada.setEmail(pessoaAtualizar.getEmail());

        PessoaEntity pessoaEntityAtualizada = pessoaRepository.save(pessoaEntityRecuperada);

//        emailService.mandarEmailAcaoCadastro(pessoaAtualizar.getNome(), pessoaAtualizar.getEmail(),
//                TipoEntidade.PESSOA, TipoAcao.ATUALIZAR);

        return objectMapper.convertValue(pessoaEntityAtualizada, PessoaDTO.class);
    }

    public void deletarPessoa(Integer id) throws RegraDeNegocioException {
        PessoaEntity pessoaEntityRecuperada = listarPessoaPeloId(id);
        pessoaRepository.delete(pessoaEntityRecuperada);

        String nome = pessoaEntityRecuperada.getNome();
        String email = pessoaEntityRecuperada.getEmail();
//        emailService.mandarEmailAcaoCadastro(nome, email, TipoEntidade.PESSOA, TipoAcao.DELETAR);
    }

    public List<PessoaEnderecoDTO> listarEnderecoPessoa(Integer idPessoa) {
        if (idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(this::getPessoaEnderecoDTO)
                    .toList();
        }

        return pessoaRepository.findById(idPessoa).stream()
                .map(this::getPessoaEnderecoDTO)
                .toList();
    }

    private PessoaEnderecoDTO getPessoaEnderecoDTO(PessoaEntity pessoa) {
        PessoaEnderecoDTO pessoaEnderecoDTO = objectMapper.convertValue(pessoa, PessoaEnderecoDTO.class);
        pessoaEnderecoDTO.setEnderecos(pessoa.getEnderecos().stream()
                .map(enderecoEntity -> objectMapper.convertValue(enderecoEntity, EnderecoDTO.class))
                .collect(Collectors.toSet()));
        return pessoaEnderecoDTO;
    }

    public List<PessoaContatoDTO> listarContatoPessoa(Integer idPessoa) {
        if (idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(this::getPessoaContatoDTO)
                    .toList();
        }

        return pessoaRepository.findById(idPessoa).stream()
                .map(this::getPessoaContatoDTO)
                .toList();
    }

    private PessoaContatoDTO getPessoaContatoDTO(PessoaEntity pessoa) {
        PessoaContatoDTO pessoaContatoDTO = objectMapper.convertValue(pessoa, PessoaContatoDTO.class);
        pessoaContatoDTO.setContatos(pessoa.getContatos().stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                .collect(Collectors.toSet()));
        return pessoaContatoDTO;
    }

    public List<PessoaFilmeDTO> listarFilmesPessoa(Integer idPessoa) {
        if (idPessoa == null) {
            return pessoaRepository.findAll().stream()
                    .map(this::getPessoaFilmeDTO)
                    .toList();
        }

        return pessoaRepository.findById(idPessoa).stream()
                .map(this::getPessoaFilmeDTO)
                .toList();
    }


    private PessoaFilmeDTO getPessoaFilmeDTO(PessoaEntity pessoa) {
        PessoaFilmeDTO pessoaFilmeDTO = objectMapper.convertValue(pessoa, PessoaFilmeDTO.class);

        pessoaFilmeDTO.setPessoaFilme(pessoa.getPessoaFilme().stream()
                .map(pessoaFilme -> objectMapper.convertValue(pessoaFilme, PessoaFilmeNovoDTO.class))
                .toList());

        return pessoaFilmeDTO;
    }

    public List<RelatorioPersonalizadoDTO> listarRelatorioPersonalizado(Integer idPessoa) {
        return pessoaRepository.listarRelatorioPersonalizado(idPessoa);
    }
}
