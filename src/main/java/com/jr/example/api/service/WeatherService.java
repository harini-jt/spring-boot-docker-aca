package com.jr.example.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jr.example.api.dto.WeatherResponseDTO;

import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.create("https://api.open-meteo.com/v1/");
    }

    public Mono<WeatherResponseDTO> getWeatherForecast(double latitude, double longitude, String hourly) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("hourly", hourly)
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponseDTO.class);
    }
}

// public class WeatherService {
   
//     public Mono<WeatherResponse> sayHi(String name){
//         Assert.notNull(name, "Input param 'name' cannot be null for method sayHello(String name)");
//         String url = "https://api.open-meteo.com/v1/forecast?latitude=57.7072&longitude=11.9668&hourly=temperature_2m";
//         WebClient.Builder builder = WebClient.builder();
//         Mono<String> response = builder.build().get().uri(url).retrieve().bodyToMono(String.class);

        
        
//         return Mono.just(WeatherResponse.builder()
//                                 .latitude("57.7072")
//                                 .longitude("11.9668")
//                                 .timezone("GMT")
//                                 .build());
//     } 
// }
