package com.beesion.ms.test.service;

import com.beesion.ms.model.Person;

public interface IPersonService {
	
	public void save(Person per);
	public void delete(Long id);
	public void update(Long id, Person per);

}
