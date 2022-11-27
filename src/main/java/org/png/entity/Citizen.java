package org.png.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String gender;

    @OneToMany(mappedBy = "citizen", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<SimCard> simCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<SimCard> getSimCard() {
        return simCard;
    }

    public void setSimCard(List<SimCard> simCard) {
        this.simCard = simCard;
    }
}
