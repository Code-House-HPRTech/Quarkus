package org.png.resource;

import org.png.entity.Citizen;
import org.png.entity.SimCard;
import org.png.repository.CitizenRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class Resource {

    @Inject
    CitizenRepository citizenRepository;

    @GET
    @Path("/save")
    @Transactional
    public Response saveData(){

        Citizen citizen = new Citizen();
        citizen.setName("Ramesh");
        citizen.setGender("M");

        SimCard simCard1 = new SimCard();
        simCard1.setActive(true);
        simCard1.setNumber(9987L);
        simCard1.setProvider("Jio");
        simCard1.setCitizen(citizen);

        SimCard simCard2 = new SimCard();
        simCard2.setActive(true);
        simCard2.setNumber(8778L);
        simCard2.setProvider("Airtel");
        simCard2.setCitizen(citizen);

        SimCard simCard3 = new SimCard();
        simCard3.setActive(true);
        simCard3.setNumber(6786L);
        simCard3.setProvider("Jio");
        simCard3.setCitizen(citizen);

        citizen.setSimCard(List.of(simCard1,simCard2,simCard3));

        citizenRepository.persist(citizen);

        if(citizenRepository.isPersistent(citizen)){
            return  Response.ok(new String("Citizen with Sim saved Successfully")).build();
        }
        return  Response.ok(new String("Something went wrong")).build();
    }

}
