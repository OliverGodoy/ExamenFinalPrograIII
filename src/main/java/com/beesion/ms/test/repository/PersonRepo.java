package com.beesion.ms.test.repository;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.impl.IPersonRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonRepo implements IPersonRepo {

	@Inject
	EntityManager em;

	@Override
	@Transactional
	public void save(Person per) {
		em.persist(per);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Person person = findById(id);
		if (person != null) {
			em.remove(person);
		}
	}

	@Override
	public Person findById(Long id) {
		return em.find(Person.class, id);
	}

	@Override
	@Transactional
	public Person merge(Person per) {
		return em.merge(per);
	}


}
