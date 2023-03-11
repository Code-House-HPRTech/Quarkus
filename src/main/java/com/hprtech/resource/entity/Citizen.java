package com.hprtech.resource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message = "Name cannot be blank")
    @Length(min = 10, max = 50,message = "Name should be between 10 char to 50 char max")
    String name;
    @Pattern(regexp = "[MF]",message = "Value can be either M or F")
    String gender;
    @Length(min = 12, max = 12, message = "Aadhar number must be 12 char")
    String aadharNo;
    @Min(value = 10,message = "Min age should be 10 yr")
    @Max(value = 24,message = "Max age should be 24 yr")
    int age;

    public Citizen() {
    }

    public Citizen(String name, String gender, String aadharNo, int age) {
        this.name = name;
        this.gender = gender;
        this.aadharNo = aadharNo;
        this.age = age;
    }

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

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}