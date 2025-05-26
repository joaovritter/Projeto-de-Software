package com.mjwsolucoes.sistemanutricao.service;

import com.mjwsolucoes.sistemanutricao.model.Nutricionista;
import com.mjwsolucoes.sistemanutricao.repository.NutricionistaRepository;

public class NutricionistaService {
    private NutricionistaRepository nutricionistaRepository;
    public NutricionistaService(NutricionistaRepository nutricionistaRepository) {
        this.nutricionistaRepository = nutricionistaRepository;
    }
    public Nutricionista create(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }
}
