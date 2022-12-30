package com.hprtech.repository;


import com.hprtech.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {

    public List<Student> getStudentListByBranch(String branch) {
        return list("Select s from Student s where s.branch=?1", branch);
    }
}
