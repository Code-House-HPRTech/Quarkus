package com.hprtech.resource.repository;

import com.hprtech.resource.entity.Citizen;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitizenRepository implements PanacheRepository<Citizen> {
}
