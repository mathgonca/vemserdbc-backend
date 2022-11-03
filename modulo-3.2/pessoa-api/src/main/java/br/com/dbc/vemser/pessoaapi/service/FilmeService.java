package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.FilmeCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.FilmeDTO;
import br.com.dbc.vemser.pessoaapi.entity.FilmeEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.FilmeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final ObjectMapper objectMapper;

    public List<FilmeDTO> listarFilmes() {
        return filmeRepository.findAll().stream()
                .map(filmeEntity -> objectMapper.convertValue(filmeEntity, FilmeDTO.class))
                .toList();
    }

    public FilmeDTO cadastrarFilme(FilmeCreateDTO filmeCreateDTO) {
        FilmeEntity filme = objectMapper.convertValue(filmeCreateDTO, FilmeEntity.class);
        return objectMapper.convertValue(filmeRepository.save(filme), FilmeDTO.class);
    }

    public FilmeDTO listarFilmeDTOPeloId(Integer idFilme) throws RegraDeNegocioException {
        return objectMapper.convertValue(listarFilmePeloId(idFilme), FilmeDTO.class);
    }

    public FilmeEntity listarFilmePeloId(Integer idFilme) throws RegraDeNegocioException {
        return filmeRepository.findById(idFilme)
                .orElseThrow(() -> new RegraDeNegocioException("Não foi encontrado um Filme com o Id procurado."));
    }

    public FilmeDTO atualizarFilme(Integer idFilme, FilmeCreateDTO filmeCreateDTO) throws RegraDeNegocioException {
        FilmeEntity filme = listarFilmePeloId(idFilme);

        filme.setDescricao(filmeCreateDTO.getDescricao());
        filme.setNota(filmeCreateDTO.getNota());
        filme.setTipoFilme(filmeCreateDTO.getTipoFilme());

        FilmeEntity filmeSalvo = filmeRepository.save(filme);

        return objectMapper.convertValue(filmeSalvo, FilmeDTO.class);
    }

    public void deletarFilme(Integer idFilme) throws RegraDeNegocioException {
        FilmeEntity filmeEntity = listarFilmePeloId(idFilme);
        filmeRepository.delete(filmeEntity);
    }
}
