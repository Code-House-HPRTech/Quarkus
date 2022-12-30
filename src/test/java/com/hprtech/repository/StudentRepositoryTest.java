package com.hprtech.repository;

import com.hprtech.entity.Student;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StudentRepositoryTest {

    @Inject
    StudentRepository studentRepository;

    @Test
    void listAll(){
        List<Student> studentList = studentRepository.listAll();

        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(),6);
        assertEquals(studentList.get(0).getName(),"Vikash");
    }

    @Test
    void findById(){
        Student student = studentRepository.findById(6L);

        assertNotNull(student);
        assertEquals(student.getStudentId(),6L);
        assertEquals(student.getName(),"Rahul");
        assertEquals(student.getBranch(),"ME");

    }

    @Test
    void getStudentListByBranch(){
        List<Student> studentList = studentRepository.getStudentListByBranch("EE");

        assertFalse(studentList.isEmpty());
        assertEquals(studentList.size(),2);

    }
}