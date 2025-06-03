package com.joaozao.arduinomvc.service;

import com.joaozao.arduinomvc.comunicacaoserial.ControlePorta;
import com.joaozao.arduinomvc.model.Led;
import com.joaozao.arduinomvc.repository.LedRepository;
import org.springframework.stereotype.Service;

@Service
public class ArduinoService {
    private ControlePorta controlePorta;
    private LedRepository ledRepository;

    public ArduinoService(LedRepository ledRepository) {
        this.ledRepository = ledRepository;
    }

    public synchronized void ligarLed() {
        abrirPorta();
        controlePorta.enviaDados('1');
        Led led = new Led();
        led.setStatus(true);
        led.setDate(new java.util.Date());
        ledRepository.save(led);
    }

    public synchronized void desligarLed() {
        abrirPorta();
        controlePorta.enviaDados('2');
        Led led = new Led();
        led.setStatus(false);
        led.setDate(new java.util.Date());
        ledRepository.save(led);
    }

    public synchronized void fecharPorta() {
        if (controlePorta != null) {
            controlePorta.close();
            controlePorta = null;
        }
    }

    private void abrirPorta() {
        if (controlePorta == null) {
            controlePorta = new ControlePorta("COM4", 9600);
        }
    }
}