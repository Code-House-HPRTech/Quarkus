package org.png.resource;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.png.entity.SimCard;
import org.png.repository.SimCardRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class Resource {

    @Inject
    SimCardRepository simCardRepository;

    @POST
    @Transactional
    @Path("/persist_SimCard")
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveSimCard(@RequestBody SimCard simCard) {
        simCardRepository.persist(simCard);
        if (simCardRepository.isPersistent(simCard)) {
            return Response.ok(new String("Sim Card saved successfully")).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/listAll_SimCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllSimCard() {
        List<SimCard> simCards = simCardRepository.listAll();
        return Response.ok(simCards).build();
    }

    @GET
    @Path("/findById_SimCard/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdSimCard(@PathParam("id") Long simCardId) {
        SimCard simCard = simCardRepository.findById(simCardId);
        return Response.ok(simCard).build();
    }

    @GET
    @Path("/count_SimCard")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countSimCard(@PathParam("id") Long simCardId) {
        long count = simCardRepository.count();
        return Response.ok(count).build();
    }

    @GET
    @Path("/provider_list_SimCard/{provider}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response providerListSimCard(@PathParam("provider") String simCardProvider) {
        List<SimCard> simCardList = simCardRepository.list("provider", simCardProvider);
        return Response.ok(simCardList).build();
    }

    @GET
    @Path("/active_list_SimCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activeListSimCard() {
        List<SimCard> simCardList = simCardRepository.list("isActive", true);
        return Response.ok(simCardList).build();
    }

}
