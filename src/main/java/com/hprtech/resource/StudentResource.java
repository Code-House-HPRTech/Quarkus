package com.hprtech.resource;


import com.hprtech.entity.Student;
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
    public Response addStudent(@RequestBody Student student) {
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
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentRepository.findById(id);
        if (student == null)
            return Response.ok(Response.status(Response.Status.NOT_FOUND)).build();
        else
            return Response.ok(student).build();
    }

    @GET
    @Path("getAllStudent")
    @Transactional
    public Response getStudentList() {
        return Response.ok(studentRepository.listAll()).build();
    }


}
