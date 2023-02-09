package org.png.resource;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.png.dto.CitizenDTO;
import org.png.entity.Citizen;
import org.png.mapper.CitizenMapper;
import org.png.repository.CitizenRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    CitizenRepository citizenRepository;

    @Inject
    CitizenMapper citizenMapper;

    @POST
    @Path("/add")
    @Transactional
    public Response addCitizen(@RequestBody CitizenDTO citizenDTO) {
        Citizen citizen = citizenMapper.toDAO(citizenDTO);
        return Response.ok(citizen).build();
    }

    @GET
    @Transactional
    @Path("/citizen/{id}")
    public Response findCitizen(@PathParam("id") Long id) {
        Citizen citizen = citizenRepository.findById(id);
        if (citizen != null) {
            CitizenDTO citizenDTO = citizenMapper.toDTO(citizen);
            citizenDTO.setFullName("Hello " + citizenDTO.getFullName());
            return Response.ok(citizenDTO).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    @Path("/citizen/{id}")
    public Response addCitizen(@PathParam("id") Long id, @RequestBody CitizenDTO citizenDTOUpdate) {
        Citizen dbCitizen = citizenRepository.findById(id);

        if (dbCitizen != null) {

            citizenMapper.merge(dbCitizen, citizenMapper.toDAO(citizenDTOUpdate));

            citizenRepository.persist(dbCitizen);

            return Response.ok(dbCitizen).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
