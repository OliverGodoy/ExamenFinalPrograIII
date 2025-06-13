package com.beesion.ms.test.repository.impl;

import com.beesion.ms.model.Person;
import jakarta.transaction.Transactional;

public interface IPersonRepo {
	
	public void save(Person per);

	public void delete(Long id);

	public Person findById(Long id);

	public Person merge(Person per);


}
