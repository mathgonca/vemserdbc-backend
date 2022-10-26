package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.entity.Contato;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ContatoRepository;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoAcao;
import br.com.dbc.vemser.pessoaapi.service.enums.TipoEntidade;
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
    private final EmailService emailService;
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
        Pessoa pessoa = pessoaService.listarPessoaPeloId(idPessoa);

        Contato contatoCadastro = objectMapper.convertValue(contatoCreateDTO, Contato.class);
        Contato contato = contatoRepository.cadastrarContato(idPessoa, contatoCadastro);

        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.CONTATO, TipoAcao.CADASTRAR);

        return objectMapper.convertValue(contato, ContatoDTO.class);
    }

    public Contato listarContatoPeloId(Integer id) throws RegraDeNegocioException {
        return contatoRepository.listarContatos().stream()
                .filter(contato -> contato.getIdContato().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
    }

    public ContatoDTO atualizarContato(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        Contato contatoRecuperado = listarContatoPeloId(id);

        contatoRecuperado.setTipoContato(contatoCreateDTO.getTipoContato());
        contatoRecuperado.setNumero(contatoCreateDTO.getNumero());
        contatoRecuperado.setDescricao(contatoCreateDTO.getDescricao());

        Contato contatoAtualizado = listarContatoPeloId(id);

        Pessoa pessoa = pessoaService.listarPessoaPeloId(contatoAtualizado.getIdPessoa());
        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.CONTATO, TipoAcao.ATUALIZAR);

        return objectMapper.convertValue(contatoAtualizado, ContatoDTO.class);
    }

    public void deletarContato(Integer id) throws RegraDeNegocioException {
        Contato contatoDeletado = listarContatoPeloId(id);
        contatoRepository.deletarContato(contatoDeletado);

        Pessoa pessoa = pessoaService.listarPessoaPeloId(contatoDeletado.getIdPessoa());
        emailService.mandarEmailAcaoCadastro(pessoa.getNome(), pessoa.getEmail(), TipoEntidade.CONTATO, TipoAcao.DELETAR);
    }
}
