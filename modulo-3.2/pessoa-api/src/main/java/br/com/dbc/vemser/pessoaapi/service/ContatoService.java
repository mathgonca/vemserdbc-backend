package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.contato.ContatoUpdateDTO;
import br.com.dbc.vemser.pessoaapi.entity.ContatoEntity;
import br.com.dbc.vemser.pessoaapi.entity.PessoaEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public ContatoDTO setContatoDTO (ContatoEntity contato) {
        ContatoDTO contatoDTO = objectMapper.convertValue(contato, ContatoDTO.class);

        Integer idPessoa = contato.getPessoa().getIdPessoa();
        contatoDTO.setIdPessoa(idPessoa);

        return contatoDTO;
    }

    public List<ContatoDTO> listarContatos() {
        return contatoRepository.findAll().stream()
                .map(this::setContatoDTO)
                .toList();
    }

    public ContatoDTO cadastrarContato(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(idPessoa);
        ContatoEntity contatoEntityCadastro = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        contatoEntityCadastro.setPessoa(pessoa);

        ContatoEntity contatoSalvo = contatoRepository.save(contatoEntityCadastro);
        return setContatoDTO(contatoSalvo);
    }

    public ContatoEntity listarContatoPeloId(Integer id) throws RegraDeNegocioException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Contato não cadastrado com Id procurado."));
    }

    public List<ContatoDTO> listarContatoPeloIdPessoa(Integer idPessoa) {
        return contatoRepository.findAllByPessoaIdPessoa(idPessoa).stream()
                .map(this::setContatoDTO)
                .toList();
    }

    public ContatoDTO atualizarContato(Integer id, ContatoUpdateDTO contatoUpdateDTO) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = listarContatoPeloId(id);
        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(contatoUpdateDTO.getIdPessoa());

        contatoEntityRecuperado.setTipoContato(contatoUpdateDTO.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoUpdateDTO.getNumero());
        contatoEntityRecuperado.setDescricao(contatoUpdateDTO.getDescricao());
        contatoEntityRecuperado.setPessoa(pessoa);

        ContatoEntity contatoAtualizado = contatoRepository.save(contatoEntityRecuperado);
        return setContatoDTO(contatoAtualizado);
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoEntityDeletado = listarContatoPeloId(id);
        contatoRepository.delete(contatoEntityDeletado);
    }
}
