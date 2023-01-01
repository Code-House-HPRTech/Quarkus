package com.hprtech.resource;


import com.hprtech.config.CustomConfig;
import com.hprtech.constant.Constants;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class Resource {

    @Inject
    CustomConfig customConfig;

    @ConfigProperty(name = "interest_rate",defaultValue = "10")
    int interestRate;

    @GET
    @Path("calc_interest/{amount}")
    public Response calcInterest(@PathParam("amount") int amount){
        return Response.ok(amount*interestRate/100 ).build();
    }

    @GET
    @Path("calc_interest/{branch}/{amount}")
    public Response calcInterest(@PathParam("branch") String branch,@PathParam("amount") int amount){


        Integer value = ConfigProvider
                .getConfig().
                getOptionalValue(branch.toLowerCase() + "_interest_rate", Integer.class)
                .orElse(5);

        return Response.ok(amount*value.intValue()/100 ).build();
    }
}
