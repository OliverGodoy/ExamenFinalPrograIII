package com.beesion.ms.test.resource;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.PersonDto;
import com.beesion.ms.test.service.impl.PersonService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/Person")
public class PersonaResource {

	@Inject
	PersonService person;

	@POST
	public Response save(PersonDto per) {
		Person p = new Person();
		p.setName(per.getName());
		person.save(p);
		
		return Response.ok("Elemento guardado").build();
	}

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, PersonDto per) {
		try {
			Person p = new Person();
			p.setName(per.getName());
			person.update(id, p);
			return Response.ok("Elemento actualizado").build();
		} catch (Exception e) {
			return Response.status(400).entity("Error al actualizar: " + e.getMessage()).build();
		}
	}


	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		try{
			person.delete(id);
			return Response.ok("Elemento eliminado").build();
		} catch (Exception e) {
			return Response.status(400).entity("Error al eliminar: " + e.getMessage()).build();
		}
	}


}
