package org.png.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/laptop")
public class LaptopResource {

    @Inject
    LaptopRepository laptopRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLaptop() {
        List<Laptop> laptopList = laptopRepository.listAll();
        return Response.ok(laptopList).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveLaptop(Laptop laptop) {
        laptopRepository.persist(laptop);
        if (laptopRepository.isPersistent(laptop)) {
            return Response.created(URI.create("/laptop/" + laptop.id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaptopById(@PathParam("id") Long id) {
        Laptop laptop = laptopRepository.findById(id);
        return Response.ok(laptop).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLaptop(@PathParam("id") Long id, Laptop updateLaptop) {
        Optional<Laptop> optionalLaptop = laptopRepository.findByIdOptional(id);
        if (optionalLaptop.isPresent()) {
            Laptop dbLaptop = optionalLaptop.get();

            if (Objects.nonNull(updateLaptop.getName())) {
                dbLaptop.setName(updateLaptop.getName());
            }
            if (Objects.nonNull(updateLaptop.getBrand())) {
                dbLaptop.setBrand(updateLaptop.getBrand());
            }
            if (updateLaptop.getRam()!=0) {
                dbLaptop.setRam(updateLaptop.getRam());
            }
            if (updateLaptop.getExternalStorage()!=0) {
                dbLaptop.setExternalStorage(updateLaptop.getExternalStorage());
            }

            laptopRepository.persist(dbLaptop);
            if (laptopRepository.isPersistent(dbLaptop)){
                return Response.created(URI.create("/laptop/" + id)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLaptop(@PathParam("id") Long id) {
        boolean isDeleted = laptopRepository.deleteById(id);
        if (isDeleted){
            return Response.noContent().build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
