package com.hprtech.resource;


import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")

@SecurityScheme(
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
public class Resource {

    @GET
    @RolesAllowed({"teacher","admin"})
    @Path("/getList")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCourseList(){
        return Response.ok("Here are course list").build();
    }

    @GET
    @RolesAllowed({"admin"})
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCourse(){
        return Response.ok("Here are course list please delete").build();
    }
}
