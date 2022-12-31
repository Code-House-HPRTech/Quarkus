package com.hprtech.resource;


import com.hprtech.entity.Student;
import com.hprtech.repository.StudentRepository;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @POST
    public Response createStudent(@RequestBody Student student) {
        studentRepository.persist(student);
        if (studentRepository.isPersistent(student))
            //localhost:8080/student/id
            return Response.created(URI.create("/student/" + student.getStudentId())).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    public Response getAllStudent() {
        List<Student> studentList = studentRepository.listAll();
        return Response.ok(studentList).build();
    }

    @GET
    @Path("branch/{branch}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCSStudentList(@PathParam("branch") String branch) {
        List<Student> studentList = studentRepository.list("branch", branch);
        return Response.ok(studentList).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentRepository.findById(id);
        if (student != null)
            return Response.ok(student).build();
        else
            return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("update/{id}")
    public Response updateStudent(@RequestBody Student studentUpdate, @PathParam("id") Long id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setName(studentUpdate.getName());
            return Response.ok(student).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("delete/{id}")
    public Response updateStudent(@PathParam("id") Long id) {
        boolean isDeleted = studentRepository.deleteById(id);
        if (isDeleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
