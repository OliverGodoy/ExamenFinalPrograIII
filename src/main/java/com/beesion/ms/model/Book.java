package com.beesion.ms.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Book {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private int numPages;
	private LocalDate pubDate;
	private String description;


}
