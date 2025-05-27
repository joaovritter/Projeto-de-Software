package com.mjwsolucoes.sistemanutricao.service;

import com.mjwsolucoes.sistemanutricao.dto.CreateNutricionistaDto;
import com.mjwsolucoes.sistemanutricao.dto.NutricionistaResponseDto;
import com.mjwsolucoes.sistemanutricao.model.Nutricionista;
import com.mjwsolucoes.sistemanutricao.repository.NutricionistaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Service
public class NutricionistaService implements UserDetailsService {
    private final NutricionistaRepository nutricionistaRepository;
    private final PasswordEncoder passwordEncoder;

    public NutricionistaService(NutricionistaRepository nutricionistaRepository, PasswordEncoder passwordEncoder) {
        this.nutricionistaRepository = nutricionistaRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public NutricionistaResponseDto create(CreateNutricionistaDto dto) {
        
        if (nutricionistaRepository.findByUsername(dto.username()).isPresent()) {
            throw new IllegalArgumentException("Usuário já existe");
        }
        Nutricionista nutricionista = new Nutricionista();
        nutricionista.setUsername(dto.username());
        nutricionista.setPassword(passwordEncoder.encode(dto.password()));
        nutricionista.setRole(dto.role());

        Nutricionista saved = nutricionistaRepository.save(nutricionista);
        return mapToResponseDto(saved);
    }

    // Método que carrega os detalhes do usuário com base no nome de usuário.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo nome de usuário.
        // Lança uma exceção UsernameNotFoundException se o usuário não for encontrado.
        Nutricionista nutricionista = nutricionistaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        // Retorna uma implementação de UserDetails com as informações do usuário.
        return new org.springframework.security.core.userdetails.User(
                nutricionista.getUsername(), // Nome de usuário.
                nutricionista.getPassword(), // Senha criptografada.
                // Concede uma autoridade baseada no papel do usuário (ex.: ROLE_USER ou ROLE_ADMIN).
                Collections.singleton(new SimpleGrantedAuthority(nutricionista.getRole()))
        );
    }

    private NutricionistaResponseDto mapToResponseDto(Nutricionista nutricionista) {
        return new NutricionistaResponseDto(
            nutricionista.getId(),
            nutricionista.getUsername(),
            nutricionista.getRole()
        );
    }

}
