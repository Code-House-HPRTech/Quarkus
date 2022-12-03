package org.png.resource;

import org.png.entity.SimCard;
import org.png.repository.SimCardRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class Resource {

    @Inject
    SimCardRepository simCardRepository;
    
    @GET
    @Path("save_simcard")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveSimCard(){
        String[] provider = {"Jio","Airtel","VI","Aircel","BSNL"};

        for(long i=0L;i<20L;i++){
            SimCard simCard = new SimCard();
            simCard.setNumber(8876223210L +i);
            simCard.setProvider(provider[(int)i%provider.length]);
            simCard.setActive(i/3L==0);

            simCardRepository.persist(simCard);
            if(simCardRepository.isPersistent(simCard)){
                System.out.println(simCard + " saved Successfully");
            }else{
                System.out.println(simCard + " not saved. Please check");
            }
        }

        return Response.ok(new String("Sim Card Saved Successfully")).build();
    }

    @GET
    @Path("test_methods")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response testMethods(){
        List<SimCard> simCards = simCardRepository.listAll();
        return Response.ok(simCards).build();
    }

}
