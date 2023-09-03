package com.jr.example.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jr.example.api.dto.LocationRequest;
import com.jr.example.api.dto.WeatherResponseDTO;
import com.jr.example.api.service.WeatherService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping("/weather")
    public Mono<WeatherResponseDTO> getWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("hourly") String hourly) {     
        return weatherService.getWeatherForecast(latitude, longitude, hourly);

    }

    @PostMapping("/weather")
    public Mono<WeatherResponseDTO> weather(
            @RequestBody LocationRequest locationRequest 
    ) {
        double latitude = locationRequest.getLatitude();
        double longitude = locationRequest.getLongitude();
        String hourly = locationRequest.getHourly();
        // Optional<String> timezone = locationRequest.getTimezone();
        return weatherService.getWeatherForecast(latitude, longitude, hourly);
    }
}