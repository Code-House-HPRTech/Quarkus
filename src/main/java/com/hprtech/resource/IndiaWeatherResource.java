package com.hprtech.resource;


import com.hprtech.restclient.NasaWeatherRestClient;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.temporal.ChronoUnit;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class IndiaWeatherResource {

    public static final Logger LOGGER = Logger.getLogger(IndiaWeatherResource.class);


    @RestClient
    NasaWeatherRestClient weatherRestClient;

    @GET
    @Path("weather/{country}")
    @Fallback(fallbackMethod = "getWeatherByCountryFallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 60,
            delayUnit = ChronoUnit.SECONDS)
    public Response getWeatherByCountry(@PathParam("country") String country) {
        LOGGER.info("Calling NasaWeatherResource::getWeatherByCountry");
        return weatherRestClient.weatherByCountry(country);
    }

    public Response getWeatherByCountryFallback(String country) {
        return Response.ok("Server is down, Please wait").build();
    }

}
