package com.hprtech.resource;

import com.hprtech.entity.Student;
import com.hprtech.repository.StudentRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentResourceTest {

    @Inject
    StudentResource studentResource;

    @InjectMock
    StudentRepository studentRepository;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setName("Aakash");
        student.setBranch("CS");
    }

    @Test
    void createStudent() {
        Student s = new Student(1L,"Rahul","CS");

        Mockito.doNothing().when(studentRepository).persist(s);
        Mockito.when(studentRepository.isPersistent(s)).thenReturn(true);

        Response response = studentResource.createStudent(s);

        assertNotNull(response);
        assertNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
        assertNotNull(response.getLocation());
    }

    @Test
    void getAllStudent() {
        List<Student> studentList =  new ArrayList<>();
        studentList.add(new Student(1L,"Shruti","CS"));
        studentList.add(new Student(2L,"Rahul","CS"));
        studentList.add(new Student(3L,"Aakanksha","ME"));

        Mockito.when(studentRepository.listAll()).thenReturn(studentList);

        Response response = studentResource.getAllStudent();

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        List<Student> studentList1 = (List<Student>) response.getEntity();

        assertEquals(studentList1.size(),3);


    }


    @Test
    void getStudentById() {
        Student student1 = new Student(4L,"Rajesh","ME");

        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(student1);

        Response response = studentResource.getStudentById(4L);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Student sr = (Student) response.getEntity();

        assertEquals(sr.getName(),"Rajesh");
        assertEquals(sr.getBranch(),"ME");

    }

    @Test
    void getStudentByIdKO() {

        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(null);

        Response response = studentResource.getStudentById(5L);

        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());


    }

    @Test
    void updateStudent() {
        Student student1 = new Student(4L,"Rajesh","ME");
        Mockito.when(studentRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(student1);

        Student newUpdateStudent= new Student();
        newUpdateStudent.setName("Mahesh");
        Response response = studentResource.updateStudent(newUpdateStudent, 4L);

        assertNotNull(response);
        assertNotNull(response.getEntity());
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

        Student sr = (Student) response.getEntity();

        assertEquals(sr.getName(),"Mahesh");
        assertEquals(sr.getBranch(),"ME");
    }

    @Test
    void testUpdateStudent() {
    }
}