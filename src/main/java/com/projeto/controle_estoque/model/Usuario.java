package com.projeto.controle_estoque.model;
import jakarta.persistence.*;
import lombok.Data;


@Data

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters e Setters (opcional, dependendo do uso)
}
