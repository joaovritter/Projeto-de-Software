package com.joaozao.arduinomvc.service;

import com.joaozao.arduinomvc.comunicacaoserial.ControlePorta;
import org.springframework.stereotype.Service;

@Service
public class ArduinoService {
    private ControlePorta controlePorta;

    public synchronized void ligarLed() {
        abrirPorta();
        controlePorta.enviaDados('1');
    }

    public synchronized void desligarLed() {
        abrirPorta();
        controlePorta.enviaDados('2');
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