package com.jr.example.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jr.example.api.dto.Message;
import com.jr.example.api.service.GreetingService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/greeting")
public class HelloWorld {
  @Autowired
  private GreetingService greetService;
  
  @GetMapping("/hello")
  public Mono<Message> sayHello(@RequestParam Optional<String> name) {
    return greetService.sayHello(name.orElseGet(()->"PHANTOM"));
  }
}
