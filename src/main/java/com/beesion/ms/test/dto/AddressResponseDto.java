package com.beesion.ms.test.dto;

import com.beesion.ms.model.Address;
import lombok.Data;

@Data
public class AddressResponseDto {
    private Long id;
    private String street;
    private String city;
    private String country;

    public static AddressResponseDto fromAddress(Address address) {
        AddressResponseDto dto = new AddressResponseDto();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        return dto;
    }
}

