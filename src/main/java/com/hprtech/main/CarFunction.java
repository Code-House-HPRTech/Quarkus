package com.hprtech.main;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;

public class CarFunction {

    public void setRace(@Observes StartupEvent startupEvent){
        System.out.println("Car is running....");
    }

    public void setBreak(@Observes ShutdownEvent startupEvent){
        System.out.println("Applying the break......");
    }
}
