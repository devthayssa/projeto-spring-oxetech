package com.projeto.controle_estoque.repository;

import com.projeto.controle_estoque.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aqui você pode adicionar métodos personalizados depois, tipo:
    Usuario findByEmail(String email);
}
