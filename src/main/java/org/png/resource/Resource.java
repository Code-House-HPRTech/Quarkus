package org.png.resource;

import org.png.entity.Aadhar;
import org.png.entity.Citizen;
import org.png.repository.AadharRepository;
import org.png.repository.CitizenRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {

    @Inject
    CitizenRepository citizenRepository;
    @Inject
    AadharRepository aadharRepository;

    @GET
    @Path("get")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCitizen() {

        Citizen citizen = citizenRepository.findById(1L);

        System.out.println(citizen);

        return Response.ok(citizen).build();

    }
}
