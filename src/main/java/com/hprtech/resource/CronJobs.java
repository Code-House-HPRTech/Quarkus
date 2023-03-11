package com.hprtech.resource;


import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CronJobs {

    @Scheduled(every = "10s")
    public void executeEvery10s(){
        System.out.println("I am cron job");
    }

    @Scheduled(cron = "0 55 12 * * ?")
    public void executeEveryDay(){
        System.out.println("I am executing at 12:53");
    }

}
