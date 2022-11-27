package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.png.entity.Aadhar;
import org.png.entity.Citizen;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AadharRepository implements PanacheRepository<Aadhar> {
}
