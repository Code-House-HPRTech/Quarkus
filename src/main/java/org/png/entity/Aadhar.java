package org.png.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Aadhar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long aadharNumber;
    String Company;

    @OneToOne
    @JsonBackReference
    Citizen citizen;
}
