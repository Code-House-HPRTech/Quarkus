package org.png.entity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LaptopRepository implements PanacheRepository<Laptop> {
}
