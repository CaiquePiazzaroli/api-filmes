package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.dto.AutenticacaoDTO;
import com.ifsp.apifilmes.model.Usuario;
import com.ifsp.apifilmes.repository.UsuarioRepository;
import com.ifsp.apifilmes.service.TokenService;
import com.ifsp.apifilmes.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final UsuarioRepository repository;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AutenticacaoController(UsuarioRepository repository, UsuarioService usuarioService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody AutenticacaoDTO dados) {
        // Uso dos métodos do Record (dados.senha() e dados.login())
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario novoUsuario = new Usuario(null, dados.login(), senhaCriptografada);

        repository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoDTO dados) {
        UserDetails usuario = usuarioService.loadUserByUsername(dados.login());

        if (passwordEncoder.matches(dados.senha(), usuario.getPassword())) {
            String token = tokenService.gerarToken(usuario);
            Map<String, String> body = new HashMap<>();
            body.put("token", token);
            return ResponseEntity.ok(body);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}