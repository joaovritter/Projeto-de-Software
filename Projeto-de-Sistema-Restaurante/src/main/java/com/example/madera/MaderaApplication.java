package com.example.madera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Madera.
 * Esta classe inicia a aplicação Spring Boot e configura o contexto da aplicação.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@SpringBootApplication
public class MaderaApplication {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     * 
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(MaderaApplication.class, args);
    }

}
