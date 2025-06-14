package com.beesion.ms.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Person {
	@Id
	@SequenceGenerator(name = "PersonIdGenerator")
	@GeneratedValue(generator = "PersonIdGenerator")
	private Long id;
	private String name;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
