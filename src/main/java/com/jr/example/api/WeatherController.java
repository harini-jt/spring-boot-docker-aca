package com.jr.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jr.example.api.dto.WeatherResponseDTO;
import com.jr.example.api.service.WeatherService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Mono<WeatherResponseDTO> getWeather(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("hourly") String hourly) {     
        return weatherService.getWeatherForecast(latitude, longitude, hourly);

    }
}