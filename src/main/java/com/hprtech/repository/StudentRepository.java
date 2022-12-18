package com.hprtech.repository;


import com.hprtech.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<Student> {
}
