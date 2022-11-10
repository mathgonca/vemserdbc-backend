package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.CadastroDTO;
import br.com.dbc.vemser.pessoaapi.dto.usuario.CadastroResponseDTO;
import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public Optional<UsuarioEntity> findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha);
    }

    public Optional<UsuarioEntity> findById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Optional<UsuarioEntity> findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public CadastroResponseDTO save(CadastroDTO cadastroDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senha = bCryptPasswordEncoder.encode(cadastroDTO.getSenha());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setLogin(cadastroDTO.getLogin());
        usuario.setSenha(senha);

        return objectMapper.convertValue(usuarioRepository.save(usuario), CadastroResponseDTO.class);
    }
}
