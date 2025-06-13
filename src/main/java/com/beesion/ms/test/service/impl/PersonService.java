package com.beesion.ms.test.service.impl;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.PersonRepo;
import com.beesion.ms.test.service.IPersonService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PersonService implements IPersonService{

	//@Inject
	PersonRepo personRepo = new PersonRepo();

	
	@Inject 
	PersonRepo personRepos;
	
	@Override
	public void save(Person per) {
		personRepos.save(per);
	}

	@Override
	public void delete(Long id){
		Person person = personRepos.findById(id);
		if (person == null) {
			throw new NotFoundException("No se encontró la persona con id: " + id);
		}
		personRepos.delete(id);
	}

	@Override
	@Transactional
	public void update(Long id, Person per) {
		Person person = personRepos.findById(id);
		if (person != null) {
			person.setName(per.getName());
			personRepos.merge(person);
		} else {
			throw new RuntimeException("No se encontró la persona con id: " + id);
		}
	}





}
