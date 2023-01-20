package com.hprtech.main;


import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Startup
public class CarService {

    @PostConstruct
    public void startEngine(){
        System.out.println("Starting the Engine........");
    }

    @PreDestroy
    public void stopEngine(){
        System.out.println("Stopping the Engine........");
    }

}
