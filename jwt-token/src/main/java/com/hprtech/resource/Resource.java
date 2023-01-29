package com.hprtech.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class Resource {

    @Inject
    TokenService tokenService;

    @GET
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken() {
        return tokenService.generateToken();
    }
}
