package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.ContatoEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public List<ContatoDTO> listarContatos() {
        return contatoRepository.findAll().stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                .toList();
    }

    public ContatoDTO cadastrarContato(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) {
        ContatoEntity contatoEntityCadastro = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        contatoEntityCadastro.setIdPessoa(idPessoa);

        ContatoEntity contatoEntity = contatoRepository.save(contatoEntityCadastro);

        return objectMapper.convertValue(contatoEntity, ContatoDTO.class);
    }

    public ContatoEntity listarContatoPeloId(Integer id) throws RegraDeNegocioException {
        Optional<ContatoEntity> contato = contatoRepository.findById(id);

        if (contato.isEmpty()) {
            throw new RegraDeNegocioException("Cadastro não encontrado!");
        }

        return contato.get();
    }

    public ContatoDTO atualizarContato(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = listarContatoPeloId(id);

        contatoEntityRecuperado.setTipoContato(contatoCreateDTO.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoCreateDTO.getNumero());
        contatoEntityRecuperado.setDescricao(contatoCreateDTO.getDescricao());
        contatoEntityRecuperado.setIdPessoa(contatoCreateDTO.getIdPessoa());

        ContatoEntity contatoEntityAtualizado = contatoRepository.save(contatoEntityRecuperado);

        return objectMapper.convertValue(contatoEntityAtualizado, ContatoDTO.class);
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoEntityDeletado = listarContatoPeloId(id);
        contatoRepository.delete(contatoEntityDeletado);
    }
}
