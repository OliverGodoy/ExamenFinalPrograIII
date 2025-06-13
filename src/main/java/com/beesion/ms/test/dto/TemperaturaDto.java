package com.beesion.ms.test.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class TemperaturaDto {

	private String ciudad;

	private int minima;

	private int maxima;

	@Override
	public String toString() {
		return "Temperatura [ciudad=" + ciudad + ", minima=" + minima + ", maxima=" + maxima + ", hashCode()="
				+ hashCode() + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemperaturaDto other = (TemperaturaDto) obj;
		return Objects.equals(ciudad, other.ciudad) && maxima == other.maxima && minima == other.minima;
	}

	public int hashCode() {
		return Objects.hash(ciudad, maxima, minima);
	}

	public TemperaturaDto() {
		super();
	}

	public TemperaturaDto(String ciudad, int minima, int maxima) {
		super();
		this.ciudad = ciudad;
		this.minima = minima;
		this.maxima = maxima;
	}
}
