package com.beesion.ms.test.service;

import com.beesion.ms.test.dto.PasswordDto;
import com.beesion.ms.test.dto.PolicyDomainDto;

import java.util.List;

public interface IServicePassword {
	
	PasswordDto generatePassword(PolicyDomainDto policy);

	void savePassword(PasswordDto passwordDto);
	PasswordDto findById(Long id);
	List<PasswordDto> findAll();
	void deletePassword(Long id);
	void updatePassword(Long id, PasswordDto passwordDto);


}
