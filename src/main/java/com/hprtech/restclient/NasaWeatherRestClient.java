package com.hprtech.restclient;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@RegisterRestClient(baseUri = "http://localhost:8085")
@Path("/api")
public interface NasaWeatherRestClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("weather_by_country/{country}")
    Response weatherByCountry(@PathParam("country") String country);
}
