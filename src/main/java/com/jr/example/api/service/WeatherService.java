package com.jr.example.api.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

import com.jr.example.api.dto.WeatherResponseDTO;

import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.create("https://api.open-meteo.com/v1/");
    }

    public Mono<WeatherResponseDTO> getWeatherForecast(Double latitude, Double longitude) {
        Assert.notNull(latitude, "Input params cannot be null for method getWeatherForecast(Double latitude, Double longitude)");
        Assert.notNull(longitude, "Input params cannot be null for method getWeatherForecast(Double latitude, Double longitude)");
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("hourly", "temperature_2m")
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponseDTO.class);
    }

}