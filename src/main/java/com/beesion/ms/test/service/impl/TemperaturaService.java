package com.beesion.ms.test.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.beesion.ms.model.Temperatura;
import com.beesion.ms.test.dto.TemperaturaDto;
import com.beesion.ms.test.repository.TemperaturaRepository;
import com.beesion.ms.test.service.ITemperaturaService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TemperaturaService implements ITemperaturaService{

    @Inject
    TemperaturaRepository temperaturaRepository;

    //private List<Temperatura> valores = new ArrayList<>();

    @Override
    @Transactional
    public void addTemperatura(TemperaturaDto dto) {
        Temperatura t = new Temperatura(
            dto.getCiudad(),
            dto.getMinima(),
            dto.getMaxima()
        );
        temperaturaRepository.persist(t);
        //valores.add(t);
    }

    @Override
    public List<TemperaturaDto> obtenerTemperaturas() {
        return temperaturaRepository.listAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public int maxima() {
        Temperatura maxTemp = temperaturaRepository.findMaxTemperature();
        if (maxTemp == null) {
            throw new IllegalStateException("No hay temperaturas registradas");
        }
        return maxTemp.getMaxima();
    }

    @Override
    public boolean isEmpty() {
        return temperaturaRepository.count() == 0;
        //return valores.isEmpty();
    }

    private TemperaturaDto convertToDto(Temperatura entidad) {
        return new TemperaturaDto(
                entidad.getCiudad(),
                entidad.getMinima(),
                entidad.getMaxima()
        );
    }

    @Override
    @Transactional
    public void deleteTemperatura(Long id) {
        Temperatura temperatura = temperaturaRepository.findById(id);
        if (temperatura != null) {
            temperaturaRepository.delete(temperatura);
        }
        else {
            throw new RuntimeException("No se encontró la temperatura con id: " + id);
        }
    }

    @Override
    @Transactional
    public void updateTemperatura(Long id, TemperaturaDto t) {
        Temperatura temperatura = temperaturaRepository.findById(id);
        if (temperatura == null) {
            throw new RuntimeException("No se encontró la temperatura con id: " + id);
        }

        temperatura.setCiudad(t.getCiudad());
        temperatura.setMinima(t.getMinima());
        temperatura.setMaxima(t.getMaxima());

        temperaturaRepository.persist(temperatura);
    }



}