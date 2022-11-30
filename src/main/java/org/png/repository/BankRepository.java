package org.png.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.png.entity.Bank;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankRepository implements PanacheRepository<Bank> {
}
