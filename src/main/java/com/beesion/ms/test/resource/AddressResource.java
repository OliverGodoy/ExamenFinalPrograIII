package com.beesion.ms.test.resource;

import com.beesion.ms.model.Address;
import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.AddressDto;
import com.beesion.ms.test.repository.AddressRepository;
import com.beesion.ms.test.repository.PersonRepo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressRepository addressRepository;

    @Inject
    PersonRepo personRepo;

    @POST
    public Response create(AddressDto addressDto) {
        if (addressDto.getPersonId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El ID de la persona es requerido")
                    .build();
        }

        Person person = personRepo.findById(addressDto.getPersonId());
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontró la persona con ID: " + addressDto.getPersonId())
                    .build();
        }

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setPerson(person);  // Establecemos la relación con Person

        addressRepository.save(address);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}

