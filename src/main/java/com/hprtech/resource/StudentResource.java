package com.hprtech.resource;


import com.hprtech.entity.Student;
import com.hprtech.repository.StudentRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @GET
    @Path("getStudentList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentList(){
        List<Student> studentList = studentRepository.listAll();
        return Response.ok(studentList).build();
    }

    @GET
    @Path("getCSStudentList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCSStudentList(){
        List<Student> csStudentList =  new ArrayList<>();
        List<Student> studentList = studentRepository.listAll();
        studentList.forEach(s->{
            if(s.getBranch().equalsIgnoreCase("CS")){
                csStudentList.add(s);
            }
        });
        return Response.ok(csStudentList).build();
    }
}
