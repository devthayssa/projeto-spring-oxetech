package com.projeto.controle_estoque.controller;

import com.projeto.controle_estoque.config.JwtUtil;
import com.projeto.controle_estoque.dto.LoginRequest;
import com.projeto.controle_estoque.model.Usuario;
import com.projeto.controle_estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            return "Usuário não encontrado";
        }

        boolean senhaCorreta = passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha());

        if (!senhaCorreta) {
            return "Senha incorreta";
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRole().name());
        return token;
    }
}

