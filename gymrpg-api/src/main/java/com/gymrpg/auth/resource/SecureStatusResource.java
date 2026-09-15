package com.gymrpg.auth.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

@Path("/api/secure-status")
public class SecureStatusResource {

    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSecureStatus(@Context SecurityContext securityContext) {
        String userId = securityContext.getUserPrincipal().getName();
        return "{\"message\": \"Olá, usuário de id " + userId + "! Você acessou uma rota protegida.\"}";
    }
}