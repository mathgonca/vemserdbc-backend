package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.FilmeAvaliadoDTO;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaFilmeEntity;
import br.com.dbc.vemser.pessoaapi.entity.pk.PessoaFilmeId;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaFilmeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaFilmeService {
    private final PessoaFilmeRepository pessoaFilmeRepository;
    private final PessoaService pessoaService;
    private final FilmeService filmeService;
    private final ObjectMapper objectMapper;

    public FilmeAvaliadoDTO avaliarFilme(Integer idPessoa,
                                         FilmeAvaliadoDTO avaliarfilmeAvaliadoDTO) throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = pessoaService.listarPessoaPeloId(idPessoa);
        PessoaFilmeEntity pessoaFilmeEntity = objectMapper.convertValue(avaliarfilmeAvaliadoDTO, PessoaFilmeEntity.class);

        pessoaFilmeEntity.getPessoaFilmeId().setIdPessoa(idPessoa);
        pessoaFilmeEntity.getPessoaFilmeId().setIdFilme(avaliarfilmeAvaliadoDTO.getIdFilme());

        pessoaFilmeEntity.setPessoa(pessoaEntity);
        pessoaFilmeEntity.setFilme(filmeService.listarFilmePeloId(avaliarfilmeAvaliadoDTO.getIdFilme()));

        pessoaFilmeEntity = pessoaFilmeRepository.save(pessoaFilmeEntity);

        FilmeAvaliadoDTO filmeAvaliadoDTO = objectMapper.convertValue(pessoaFilmeEntity, FilmeAvaliadoDTO.class);
        filmeAvaliadoDTO.setIdFilme(pessoaFilmeEntity.getPessoa().getIdPessoa());
        filmeAvaliadoDTO.setIdPessoa(pessoaFilmeEntity.getFilme().getIdFilme());

        return filmeAvaliadoDTO;
    }
}
