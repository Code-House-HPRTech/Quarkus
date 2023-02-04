package org.png.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String gender;

    @JsonManagedReference
    @OneToOne(mappedBy = "citizen",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Aadhar aadhar;

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", aadhar=" + aadhar +
                '}';
    }
}
