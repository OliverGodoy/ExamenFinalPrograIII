package com.beesion.ms.test.repository;

import com.beesion.ms.model.Password;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordRepository implements PanacheRepository<Password> {
}