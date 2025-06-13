package com.beesion.ms.test.resource;

import java.util.List;

import com.beesion.ms.test.dto.TemperaturaDto;
import com.beesion.ms.test.service.impl.TemperaturaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/temperatura")
public class TemperaturasResource {

	@Inject
	private TemperaturaService temperaturas;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TemperaturaDto medicion() {
		return new TemperaturaDto("Guatemala", 25, 32);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<TemperaturaDto> list() {
		return temperaturas.obtenerTemperaturas();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response guardar(TemperaturaDto temp) {
		temperaturas.addTemperatura(temp);
		return Response
				.status(Response.Status.CREATED)
				.entity(temp)
				.header("mensaje", "Temperatura guardada correctamente")
				.build();
	}

	@GET
	@Path("/maxima")
	@Produces(MediaType.TEXT_PLAIN)
	public Response maxima() {
		if(temperaturas.isEmpty())
			return Response.status(400).entity("No hay temperaturas").build();
		int tempetaturaMaxima = temperaturas.maxima();
		return Response.ok(Integer.toString(tempetaturaMaxima)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, TemperaturaDto temp) {
		try {
			temperaturas.updateTemperatura(id, temp);
			return Response.ok()
					.entity("Temperatura actualizada correctamente")
					.build();
		} catch (Exception e) {
			return Response.status(400)
					.entity("Error al actualizar: " + e.getMessage())
					.build();
		}
	}


	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {
		try {
			temperaturas.deleteTemperatura(id);
			return Response.ok("Temperatura eliminada").build();
		} catch (Exception e) {
			return Response.status(400).entity("Error al eliminar: " + e.getMessage()).build();
		}
	}


}
