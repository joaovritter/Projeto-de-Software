package com.joaozao.arduinomvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Led {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean status;
    private Date date;

    public Led() {

    }
    public Led(boolean status, Date date) {
        this.status = status;
        this.date = date;
    }

}
