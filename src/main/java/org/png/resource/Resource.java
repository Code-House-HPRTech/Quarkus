package org.png.resource;

import org.png.entity.Aadhar;
import org.png.entity.Citizen;
import org.png.repository.AadharRepository;
import org.png.repository.CitizenRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class Resource {

    @Inject
    CitizenRepository citizenRepository;
    @Inject
    AadharRepository aadharRepository;

    @GET
    @Path("save")
    @Transactional
    public Response saveCitizen(){



        Citizen citizen = new Citizen();
        citizen.setGender("M");
        citizen.setName("Rahul");

        Aadhar aadhar = new Aadhar();
        aadhar.setAadharNumber(2133L);
        aadhar.setCompany("UIDAI");
        aadhar.setCitizen(citizen);

        citizen.setAadhar(aadhar);

        citizenRepository.persist(citizen);



        return  Response.ok("Saved").build();

    }
}
