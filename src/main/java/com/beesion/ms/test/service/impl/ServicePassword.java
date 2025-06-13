package com.beesion.ms.test.service.impl;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.beesion.ms.test.dto.PasswordDto;
import com.beesion.ms.test.dto.PolicyDomainDto;
import com.beesion.ms.model.Password;
import com.beesion.ms.test.repository.PasswordRepository;
import com.beesion.ms.test.service.IServicePassword;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServicePassword implements IServicePassword {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL_CHAR = "!#.";

    private static SecureRandom random = new SecureRandom();
    
    @Inject
    PasswordRepository passwordRepository;

    @Override
    public PasswordDto generatePassword(PolicyDomainDto policy) {
        String pass = generate(policy);

        PasswordDto password = new PasswordDto();
        password.setPassword(pass);
        password.setCreatedTimestamp(LocalDate.now().toString());

        return password;
    }

    @Override
    @Transactional
    public void savePassword(PasswordDto passwordDto) {
        Password password = convertToEntity(passwordDto);
        passwordRepository.persist(password);
    }

    @Override
    public PasswordDto findById(Long id) {
        Password password = passwordRepository.findById(id);
        return password != null ? convertToDto(password) : null;
    }

    @Override
    public List<PasswordDto> findAll() {
        return passwordRepository.listAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePassword(Long id) {
        Password password = passwordRepository.findById(id);
        if (password == null) {
            throw new IllegalArgumentException("Contraseña no encontrada con id: " + id
            );
        }
        passwordRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, PasswordDto passwordDto) {
        Password password = passwordRepository.findById(id);
        if (password != null) {
            password.setPassword(passwordDto.getPassword());
            password.setCreatedTimestamp(passwordDto.getCreatedTimestamp());
            passwordRepository.persist(password);
        }
        else {
            throw new IllegalArgumentException("Contraseña no encontrada con id: " + id);
        }
    }

    // Métodos privados existentes
    private static String generate(PolicyDomainDto policy) {
        if (policy.getMinLength() < 4)
            throw new IllegalArgumentException("La longitud de la contraseña debe ser al menos 4 caracteres.");

        StringBuilder password = new StringBuilder(policy.getMinLength());
        String passwordAllowBase = "";

        if (policy.getRestrictMinLowerCaseLetters()) {
            password.append(randomChars(CHAR_LOWER, policy.getMinLowerCaseLetters()));
            passwordAllowBase = passwordAllowBase + CHAR_LOWER;
        }
        // ... resto del código de generate ...
        return password.toString();
    }

    private static String randomChars(String source, int count) {
        StringBuilder result = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            result.append(randomChar(source));
        }
        return result.toString();
    }

    private static char randomChar(String source) {
        int randomIndex = random.nextInt(source.length());
        return source.charAt(randomIndex);
    }

    // Métodos de conversión
    private PasswordDto convertToDto(Password entity) {
        PasswordDto dto = new PasswordDto();
        dto.setPassword(entity.getPassword());
        dto.setCreatedTimestamp(entity.getCreatedTimestamp());
        return dto;
    }

    private Password convertToEntity(PasswordDto dto) {
        Password entity = new Password();
        entity.setPassword(dto.getPassword());
        entity.setCreatedTimestamp(dto.getCreatedTimestamp());
        return entity;
    }
}