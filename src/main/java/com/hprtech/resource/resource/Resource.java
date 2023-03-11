package com.hprtech.resource.resource;


import com.hprtech.resource.entity.Citizen;
import com.hprtech.resource.repository.CitizenRepository;
import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("")
public class Resource {

    @Inject
    CitizenRepository citizenRepository;

    @Inject
    Validator validator;

    @POST
    @Transactional
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCitizen(Citizen citizen) {

        Set<ConstraintViolation<Citizen>> validate = validator.validate(citizen);

        if (validate.isEmpty()) {
            citizenRepository.persist(citizen);
            if (citizenRepository.isPersistent(citizen)) {
                return Response.ok(citizen).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            String errorMsg = validate.stream().map(citizenConstraintViolation -> citizenConstraintViolation.getMessage())
                    .collect(Collectors.joining(", "));

            return Response.status(Response.Status.BAD_REQUEST).entity(errorMsg).build();
        }
    }

    @POST
    @Transactional
    @Path("/saveValid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCitizenValid(@Valid Citizen citizen) {

        citizenRepository.persist(citizen);
        if (citizenRepository.isPersistent(citizen)) {
            return Response.ok(citizen).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
