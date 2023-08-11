package com.jr.example.api.service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.jr.example.api.dto.Message;

import reactor.core.publisher.Mono;

@Service
public class GreetingService {
    @Autowired
    private Supplier<Instant> serverTimeSupplier;

    public Mono<Message> sayHello(String name){
        Assert.notNull(name, "Input param 'name' cannot be null for method sayHello(String name)");
        ZonedDateTime cetTime =  serverTimeSupplier.get()
                                                   .atZone(ZoneId.of("CET"));
        int iHourOfDay = cetTime.getHour();
        String sGreeting;
        if(iHourOfDay >= 3 && iHourOfDay <12){
            sGreeting = "Good Morning"; 
        }else if(iHourOfDay >= 12 && iHourOfDay <16) {
            sGreeting = "Good Afternoon"; 
        }else if (iHourOfDay >= 16 && iHourOfDay <20){
             sGreeting = "Good Evening"; 
        }else{
            sGreeting = "Good Night";
        } 
        return Mono.just(Message.builder()
                                .text(String.format("%s, %s!!",sGreeting,name))
                                .serverTime(cetTime)
                                .build());
    } 

}
