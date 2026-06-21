package com.ifsp.apifilmes.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ifsp.apifilmes.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private String segredo = "minha-chave-secreta-super-protegida";

    public String gerarToken(UserDetails usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritmo);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(segredo);

            return JWT.require(algoritmo)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
