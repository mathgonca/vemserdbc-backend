package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.PessoaCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import br.com.dbc.vemser.pessoaapi.entity.Pessoa;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.PessoaRepository;
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
        Pessoa pessoa = objectMapper.convertValue(pessoaCreateDTO, Pessoa.class);


        return objectMapper.convertValue(pessoaRepository.cadastrarPessoa(pessoa), PessoaDTO.class);
    }

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoaList = pessoaRepository.listarPessoas();

        return pessoaList.stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .toList();
    }

    public PessoaDTO atualizarPessoa(Integer id, PessoaCreateDTO pessoaAtualizar) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = pessoaRepository.listarPessoas().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não econtrada"));

        pessoaRecuperada.setCpf(pessoaAtualizar.getCpf());
        pessoaRecuperada.setNome(pessoaAtualizar.getNome());
        pessoaRecuperada.setDataNascimento(pessoaAtualizar.getDataNascimento());

        Pessoa pessoaAtualizada = listarPessoaPeloId(id);

        return objectMapper.convertValue(pessoaAtualizada, PessoaDTO.class);
    }

    public void deletarPessoa(Integer id) throws RegraDeNegocioException {
        Pessoa pessoaRecuperada = listarPessoaPeloId(id);
        pessoaRepository.deletarPessoa(pessoaRecuperada);
    }

    public List<PessoaDTO> listarPessoaPeloNome(String nome) {
        List<Pessoa> pessoaList = pessoaRepository.listByName(nome);
        return pessoaList.stream()
                .map(pessoa -> objectMapper.convertValue(pessoa, PessoaDTO.class))
                .toList();
    }

    public Pessoa listarPessoaPeloId(Integer idPessoa) throws RegraDeNegocioException {
        Optional<Pessoa> pessoaRecuperada = pessoaRepository.listarPessoaPeloId(idPessoa);

        if (pessoaRecuperada.isEmpty()) {
            throw new RegraDeNegocioException("Pessoa não cadastrada");
        }

        return pessoaRecuperada.get();
    }
}
