package com.hprtech.resource;

import com.hprtech.restclient.JsonPlaceholderRestClient;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {
    @RestClient
    JsonPlaceholderRestClient restClient;
    private long highestPrimeNumberSoFar = 2;

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok("Hello from Quarkus").build();
    }

    /**
     * This api will take a number and return whether the input number is prime number or not
     *
     * @param number
     * @return
     */
    @POST
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "CountOf__CheckIfPrime", description = "How many time this method/api is called")
    @Timed(name = "TimeTaken__CheckIfPrime", description = "How much time api take to respond", unit = MetricUnits.MILLISECONDS)
    @Metered(name = "Metered_CheckIfPrime", description = "How freq this api is called")
    public String checkIfPrime(@PathParam("number") long number) {
        if (number < 1) {
            return "Only natural numbers can be prime numbers.";
        }
        if (number == 1) {
            return "1 is not prime.";
        }
        if (number == 2) {
            return "2 is prime.";
        }
        if (number % 2 == 0) {
            return number + " is not prime, it is divisible by 2.";
        }
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
            if (number % i == 0) {
                return number + " is not prime, is divisible by " + i + ".";
            }
        }
        if (number > highestPrimeNumberSoFar) {
            highestPrimeNumberSoFar = number;
        }
        return number + " is prime.";
    }

    @Gauge(name = "HighestPrimeNumberRequest", description = "What is the highest prime number calculated so far", unit = MetricUnits.NONE)
    public Long highestPrimeNumberSoFar() {
        return highestPrimeNumberSoFar;
    }

}
