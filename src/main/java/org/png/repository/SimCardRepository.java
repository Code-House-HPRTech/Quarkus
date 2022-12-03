package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.png.entity.SimCard;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimCardRepository implements PanacheRepository<SimCard> {
}
