package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
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
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public List<ContatoDTO> listarContatos() {
        return contatoRepository.findAll().stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                .toList();
    }

    public ContatoDTO cadastrarContato(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        PessoaEntity pessoa = pessoaService.listarPessoaPeloId(idPessoa);
        ContatoEntity contatoEntityCadastro = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        contatoEntityCadastro.setPessoa(pessoa);

        return objectMapper.convertValue(contatoRepository.save(contatoEntityCadastro), ContatoDTO.class);
    }

    public ContatoEntity listarContatoPeloId(Integer id) throws RegraDeNegocioException {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Contato não cadastrado com Id procurado."));
    }

    public List<ContatoDTO> listarContatoPeloIdPessoa(Integer idPessoa) {
        return contatoRepository.findAllByPessoaIdPessoa(idPessoa).stream()
                .map(contatoEntity -> objectMapper.convertValue(contatoEntity, ContatoDTO.class))
                .toList();
    }

    public ContatoDTO atualizarContato(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        ContatoEntity contatoEntityRecuperado = listarContatoPeloId(id);

        contatoEntityRecuperado.setTipoContato(contatoCreateDTO.getTipoContato());
        contatoEntityRecuperado.setNumero(contatoCreateDTO.getNumero());
        contatoEntityRecuperado.setDescricao(contatoCreateDTO.getDescricao());

        return objectMapper.convertValue(contatoRepository.save(contatoEntityRecuperado), ContatoDTO.class);
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoEntityDeletado = listarContatoPeloId(id);
        contatoRepository.delete(contatoEntityDeletado);
    }
}
