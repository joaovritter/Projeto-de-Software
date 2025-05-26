package com.mjwsolucoes.sistemanutricao.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Senha", nullable = false)
    private String senha;

    public Estabelecimento() {
    }

    public Estabelecimento(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }
    public Estabelecimento(Long id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estabelecimento that = (Estabelecimento) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(senha, that.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, senha);
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
