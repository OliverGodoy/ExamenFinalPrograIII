package com.beesion.ms.test.service;

import java.util.List;

import com.beesion.ms.test.dto.TemperaturaDto;

public interface ITemperaturaService {

	void addTemperatura(TemperaturaDto t);
	
	List<TemperaturaDto> obtenerTemperaturas();
	
	boolean isEmpty();
	
	int maxima();

	void deleteTemperatura(Long id);
	void updateTemperatura(Long id, TemperaturaDto t);
	
}
