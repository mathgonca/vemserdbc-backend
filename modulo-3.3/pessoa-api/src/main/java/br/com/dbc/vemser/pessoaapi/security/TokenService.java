package br.com.dbc.vemser.pessoaapi.security;

import br.com.dbc.vemser.pessoaapi.entity.UsuarioEntity;
import br.com.dbc.vemser.pessoaapi.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final UsuarioService usuarioService;
    private final String BEARER = "Bearer ";

    @Value("${jwt.expiration}")
    private String expiration;
    @Value("${jwt.secret}")
    private String secret;

    public String getToken(UsuarioEntity usuarioEntity) {
        Date now = new Date();
        LocalDateTime dataExpLocalDate = LocalDateTime.now().plusDays(Long.parseLong(expiration));
        Date exp = Date.from(dataExpLocalDate.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setIssuer("vemser-api")
                .claim(Claims.ID, usuarioEntity.getIdUsuario().toString())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public UsernamePasswordAuthenticationToken validate(String token) {
        if (token == null) {
            return null;
        }
        token = token.replace(BEARER, "");

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String idUsuario = body.get(Claims.ID, String.class);

        UsernamePasswordAuthenticationToken dtoDoSpring =
                new UsernamePasswordAuthenticationToken(idUsuario,
                        null,
                        Collections.emptyList());
        return dtoDoSpring;
    }
}
