package com.hprtech.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {

    @ConfigProperty(name="CEO",defaultValue = "CodeHouse")
    String ceo;

    @ConfigProperty(name="profile",defaultValue = "NONE")
    String profile;

    @ConfigProperty(name="interest_rate",defaultValue = "5")
    String interestRate;

    @GET
    @Path("ceo")
    public Response getCEOName(){
        return Response.ok(ceo + " " + profile +  interestRate).build();
    }

}
