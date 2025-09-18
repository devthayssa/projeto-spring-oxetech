package com.projeto.controle_estoque.controller;

import com.projeto.controle_estoque.config.JwtUtil;
import com.projeto.controle_estoque.model.Usuario;
import com.projeto.controle_estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/me")
public class UsuarioLogadoController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> getUsuarioLogado(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Token ausente ou inválido");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }

        return ResponseEntity.ok(usuario);
    }
}


