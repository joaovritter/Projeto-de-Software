package com.mjwsolucoes.sistemanutricao.model;

import jakarta.persistence.*;

@Entity
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Senha", nullable = false)
    private String senha;
}
