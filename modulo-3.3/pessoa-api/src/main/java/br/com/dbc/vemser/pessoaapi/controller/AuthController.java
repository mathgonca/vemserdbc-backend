package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.dto.usuario.CadastroDTO;
import br.com.dbc.vemser.pessoaapi.dto.usuario.CadastroResponseDTO;
import br.com.dbc.vemser.pessoaapi.dto.usuario.LoginDTO;
import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.security.TokenService;
import br.com.dbc.vemser.pessoaapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/autenticar")
    public String autenticar(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Object principal = authenticate.getPrincipal();
        UsuarioEntity usuarioEntity = (UsuarioEntity) principal;
        return tokenService.getToken(usuarioEntity);
    }

    @PostMapping("/cadastrar")
    public CadastroResponseDTO cadastrar(CadastroDTO cadastro) {
        return usuarioService.save(cadastro);
    }
}
