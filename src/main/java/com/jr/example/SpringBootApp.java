package com.jr.example;
import java.time.Instant;
import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootApp {

  @Bean
  public Supplier<Instant> getServerTimeSupplier(){
      return ()-> Instant.now();
  }
  public static void main(String[] args) {
    SpringApplication.run(SpringBootApp.class, args);
  } 
}
