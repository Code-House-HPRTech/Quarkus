package com.hprtech.resource;


import com.hprtech.entity.Student;
import com.hprtech.exception.BusinessException;
import com.hprtech.exception.TechnicalException;
import com.hprtech.repository.StudentRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @POST
    @Path("addStudent")
    @Transactional
    public Response addStudent(@RequestBody Student student) throws BusinessException {
        if(student==null || student.getName()==null || student.getName().length()==0){
            throw new BusinessException(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    "Student Name Cannot be empty");
        }
        studentRepository.persist(student);
        if (studentRepository.isPersistent(student)) {
            //localhost:8080/student/id
            return Response.created(URI.create("/student/" + student.getStudentId())).build();
        }
        return Response.ok(Response.status(Response.Status.BAD_REQUEST)).build();
    }

    @GET
    @Path("student/{id}")
    @Transactional
    public Response getStudentById(@PathParam("id") Long id) throws BusinessException {
        Student student = studentRepository.findById(id);
        if (student == null)
            throw new BusinessException(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    "Student with ID: "+ id+ " Not exist in DB");
        else
            return Response.ok(student).build();
    }

    @GET
    @Path("getAllStudent")
    @Transactional
    public Response getStudentList() {
        return Response.ok(studentRepository.listAll()).build();
    }

    @GET
    @Path("divide/{i}")
    @Transactional
    public Response testApi(@PathParam("i") int i) throws TechnicalException {
        try{
            int out = 8 / i;
            return Response.ok("Output after dividing " + out).build();
        }catch (Exception e){
            throw new TechnicalException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    e.getMessage());

        }
    }

}
