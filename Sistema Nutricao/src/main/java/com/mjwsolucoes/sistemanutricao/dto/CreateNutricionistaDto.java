package com.mjwsolucoes.sistemanutricao.dto;

import com.mjwsolucoes.sistemanutricao.model.Nutricionista;

public record CreateNutricionistaDto(String username, String password) {
    public Nutricionista toEntity() {
        Nutricionista nutricionista = new Nutricionista();
        nutricionista.setUsername(this.username);
        nutricionista.setSenha(this.password);
        return nutricionista;
    }
}