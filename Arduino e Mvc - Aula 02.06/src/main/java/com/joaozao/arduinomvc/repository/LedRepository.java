package com.joaozao.arduinomvc.repository;

import com.joaozao.arduinomvc.model.Led;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedRepository extends JpaRepository<Led, Integer> {

}