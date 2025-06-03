package com.joaozao.arduinomvc.controller;

import com.joaozao.arduinomvc.service.ArduinoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arduino")
public class ArduinoController {

    private final ArduinoService controlePortaService;

    public ArduinoController(ArduinoService controlePortaService) {
        this.controlePortaService = controlePortaService;
    }

    @GetMapping("/on")
    public String ligarLed() {
        controlePortaService.ligarLed();
        return "LED ligado";
    }

    @GetMapping("/off")
    public String desligarLed() {
        controlePortaService.desligarLed();
        return "LED desligado";
    }

    @GetMapping("/status")
    public String status() {

        return "";
    }
}