package com.gymrpg.health;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/status")
public class StatusResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StatusResponse getStatus() {
        return new StatusResponse("GymRPG API está no ar!");
    }

    public record StatusResponse(String message) {}
}