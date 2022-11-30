package org.png.resource;

import org.png.entity.Bank;
import org.png.entity.Citizen;
import org.png.repository.BankRepository;
import org.png.repository.CitizenRepository;

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
    BankRepository bankRepository;

    @Inject
    CitizenRepository citizenRepository;

    @GET
    @Path("save_bank")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveBank(){
        String[] bankNames = {"SBI","PNB","AXIS","HDFC","ICICI","KOTAK"};

        for (String bankName : bankNames){
            Bank bank = new Bank();
            bank.setBranch("IT Park, EPIP Sitapura");
            bank.setName(bankName);
            bank.setIfscCode("IFCS22"+bankName);

            bankRepository.persist(bank);
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("save_citizen")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveCitizen(){
        String[] bankNames = {"SBI","PNB","AXIS","HDFC","ICICI","KOTAK"};

        Bank SBIBank = bankRepository.find("name",bankNames[0]).firstResult();
        Bank PNBBank = bankRepository.find("name",bankNames[1]).firstResult();
        Bank AXISBank = bankRepository.find("name",bankNames[2]).firstResult();
        Bank HDFCBank = bankRepository.find("name",bankNames[3]).firstResult();
        Bank ICICIBank = bankRepository.find("name",bankNames[4]).firstResult();
        Bank KOTAKBank = bankRepository.find("name",bankNames[5]).firstResult();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + PNBBank);

        Citizen citizenRahul = new Citizen();
        citizenRahul.setName("Rahul");
        citizenRahul.setGender("M");
        citizenRahul.setBankList(List.of(SBIBank,AXISBank,ICICIBank,PNBBank));

        Citizen citizenAaka = new Citizen();
        citizenAaka.setName("Aakanksha");
        citizenAaka.setGender("F");
        citizenAaka.setBankList(List.of(SBIBank,AXISBank,KOTAKBank));

        Citizen citizenMic = new Citizen();
        citizenMic.setName("Mic");
        citizenMic.setGender("F");
        citizenMic.setBankList(List.of(AXISBank));

        citizenRepository.persist(citizenRahul);
        citizenRepository.persist(citizenAaka);
        citizenRepository.persist(citizenMic);


        return Response.status(Response.Status.OK).build();
    }

}
