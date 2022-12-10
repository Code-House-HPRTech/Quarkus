package org.png.resource;

import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.png.entity.SimCard;
import org.png.repository.SimCardRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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

    @GET
    @Path("/findByIdOptional_SimCard/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdOptionalSimCard(@PathParam("id") Long id) {
        Optional<SimCard> OptionalSimCard = simCardRepository.findByIdOptional(id);
        if (OptionalSimCard.isPresent()) {
            SimCard simCard = OptionalSimCard.get();
            return Response.ok(simCard).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/conditional_count_SimCard/{provider}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response conditionalCountSimCard(@PathParam("provider") String simProvider) {
        //select count(*) from SimCard where provider =  @PathParam("provider")
        long count = simCardRepository.count("provider", simProvider);
        return Response.ok(count).build();
    }

    @GET
    @Path("/conditional_delete_SimCard/{provider}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response conditionalDeleteSimCard(@PathParam("provider") String simProvider) {
        // delete from SimCard where provider =  @PathParam("provider")
        long delete = simCardRepository.delete("provider", simProvider);
        return Response.ok(delete).build();
    }

    @GET
    @Path("/conditionalDeleteById_SimCard/{id}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response conditionalDeleteByIdSimCard(@PathParam("id") Long id) {
        // delete from SimCard where id =  @PathParam("id")
        boolean isDeleted = simCardRepository.deleteById(id);
        if (isDeleted)
            return Response.ok("Sim card deleted successfully").build();
        else
            return Response.ok("Something went wrong").build();
    }

    @GET
    @Path("/update/{id}/{provider}")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response conditionalDeleteSimCard(@PathParam("id") Long id, @PathParam("provider") String provider) {
        // update SimCard set provider = @PathParam("provider") where id = @PathParam("id")
        int update = simCardRepository.update("provider =?1 where id =?2", provider, id);
        if (update == 1)
            return Response.ok("Sim card provider updated successfully").build();
        else
            return Response.ok("Something went wrong").build();
    }

    @GET
    @Path("/sortBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortBySimCard() {
        //select * from SimCard where isActive=false order by provider desc
        List<SimCard> simCardList = simCardRepository
                .list("isActive",
                        Sort.descending("provider"),
                        false);
            return Response.ok(simCardList).build();
    }

}
