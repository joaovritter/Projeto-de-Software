package com.joaozao.arduinomvc;

import com.joaozao.arduinomvc.comunicacaoserial.ControlePorta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}