package com.beesion.ms.test.repository;

import com.beesion.ms.model.Temperatura;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TemperaturaRepository implements PanacheRepository<Temperatura> {

    public Temperatura findMaxTemperature() {
        return find("ORDER BY maxima DESC").firstResult();
    }
}