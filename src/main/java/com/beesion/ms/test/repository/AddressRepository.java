package com.beesion.ms.test.repository;

import com.beesion.ms.model.Address;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AddressRepository {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional
    public Address save(Address address) {
        entityManager.persist(address);
        return address;
    }
    
    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList();
    }
    
    public List<Address> findByPersonId(Long personId) {
        return entityManager.createQuery(
                "SELECT a FROM Address a WHERE a.person.id = :personId", Address.class)
                .setParameter("personId", personId)
                .getResultList();
    }
}