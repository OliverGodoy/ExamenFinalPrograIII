package com.beesion.ms.test.resource;

import java.util.List;

import com.beesion.ms.test.dto.PasswordDto;
import com.beesion.ms.test.dto.PolicyDomainDto;
import com.beesion.ms.test.repository.PasswordRepository;
import com.beesion.ms.test.service.IServicePassword;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/passwords")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PasswordResource {

    @Inject
    private IServicePassword servicePassword;

    @Inject
    private PasswordRepository passwordRepository;

    @POST
    @Path("/generate")
    public Response generatePasswordDto(PolicyDomainDto p) {
        try {            
            return Response.ok(servicePassword.generatePassword(p)).build();
        } catch(Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response savePassword(PasswordDto password) {
        try {
            servicePassword.savePassword(password);
            return Response.status(Response.Status.CREATED)
                    .entity(password)
                    .header("mensaje", "Contraseña guardada correctamente")
                    .build();
        } catch(Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPassword(@PathParam("id") Long id) {
        PasswordDto password = servicePassword.findById(id);
        if (password == null) {
            return Response.status(404).entity("Contraseña no encontrada").build();
        }
        return Response.ok(password).build();
    }

    @GET
    public Response getAllPasswords() {
        List<PasswordDto> passwords = servicePassword.findAll();
        return Response.ok(passwords).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePassword(@PathParam("id") Long id, PasswordDto password) {
        try {
            servicePassword.updatePassword(id, password);
            return Response.ok()
                    .header("mensaje", "Contraseña actualizada correctamente")
                    .build();
        } catch(Exception e) {
            return Response.status(400).entity("Error al actualizar: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePassword(@PathParam("id") Long id) {
        try {
            servicePassword.deletePassword(id);
            return Response.ok()
                    .header("mensaje", "Contraseña eliminada correctamente")
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity("Error al eliminar: " + e.getMessage()).build();
        }
    }
}