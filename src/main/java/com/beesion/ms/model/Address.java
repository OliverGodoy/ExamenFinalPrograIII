package com.beesion.ms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @SequenceGenerator(name = "AddressIdGenerator")
    @GeneratedValue(generator = "AddressIdGenerator")
    private Long id;

    private String street;
    private String city;
    private String country;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}

