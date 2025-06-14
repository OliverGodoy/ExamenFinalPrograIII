package com.beesion.ms.test.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String city;
    private String country;
    private Long personId;
}