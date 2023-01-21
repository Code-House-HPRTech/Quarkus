package com.hprtech.resource;

import com.hprtech.restclient.JsonPlaceholderRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {


    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok("Hello from Quarkus").build();
    }

}
