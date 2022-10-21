package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
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
    private final PessoaService pessoaService;
    private final ObjectMapper objectMapper;

    public List<ContatoDTO> listarContatos() {
        return contatoRepository.listarContatos().stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .toList();
    }

    public List<ContatoDTO> listarContatoPeloIdPessoa(Integer idPessoa) {
        return contatoRepository.listarContatoPeloIdPessoa(idPessoa).stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .toList();
    }

    public ContatoDTO cadastrarContato(Integer idPessoa, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        pessoaService.listarPessoaPeloId(idPessoa);

        Contato contatoCadastro = objectMapper.convertValue(contatoCreateDTO, Contato.class);
        Contato contato = contatoRepository.cadastrarContato(idPessoa, contatoCadastro);

        return objectMapper.convertValue(contato, ContatoDTO.class);
    }

    public ContatoDTO atualizarContato(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        Contato contatoRecuperado = contatoRepository.listarContatoPeloId(id).stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));

        contatoRecuperado.setTipoContato(contatoCreateDTO.getTipoContato());
        contatoRecuperado.setNumero(contatoCreateDTO.getNumero());
        contatoRecuperado.setDescricao(contatoCreateDTO.getDescricao());

        Contato contatoAtualizado = contatoRepository.listarContatoPeloId(id).get();

        return objectMapper.convertValue(contatoAtualizado, ContatoDTO.class);
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        Optional<Contato> contatoDeletado = contatoRepository.listarContatoPeloId(id);

        if (contatoDeletado.isEmpty()) {
            throw new RegraDeNegocioException("Contato não encontrado");
        }

        contatoRepository.deletarContato(contatoDeletado.get());
    }
}
