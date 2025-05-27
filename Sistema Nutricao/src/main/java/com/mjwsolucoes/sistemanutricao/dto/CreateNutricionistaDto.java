package com.mjwsolucoes.sistemanutricao.dto;

import com.mjwsolucoes.sistemanutricao.model.Nutricionista;

public record CreateNutricionistaDto(String username, String password, String role) {
}