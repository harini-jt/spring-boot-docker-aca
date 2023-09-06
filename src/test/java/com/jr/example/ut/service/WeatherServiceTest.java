package com.jr.example.ut.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import com.jr.example.api.exceptions.CustomException;
import com.jr.example.api.service.WeatherService;

public class WeatherServiceTest {

    @Mock
    private WebClient webClient;

    private WeatherService weatherService = new WeatherService();

    @Test
    public void testWithNullInput()
    {
        Exception ex = assertThrows(IllegalArgumentException.class, ()-> {
            weatherService.getWeatherForecast(null, null);
        });
        String expectedMessage = "Input params cannot be null for method getWeatherForecast(Double latitude, Double longitude)";
        System.out.println(ex.getMessage());
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @Test
    public void testWithOutOfRangeLatitude()
    {
        Exception ex = assertThrows(CustomException.class, ()-> {
            weatherService.getWeatherForecast(99.00, 9.99);
        });
        String expectedMessage = "Invalid range of latitude or longitude value. Latitude must be in range of -90 to 90\u00B0. Longitude must be in range of -180 to 180\u00B0";
        System.out.println(ex.getMessage());
        assertTrue(ex.getMessage().contains(expectedMessage));
    }

    @Test
    public void testWithOutOfRangeLongitude()
    {
        Exception ex = assertThrows(CustomException.class, ()-> {
            weatherService.getWeatherForecast(90.00, 180.99);
        });
        String expectedMessage = "Invalid range of latitude or longitude value. Latitude must be in range of -90 to 90\u00B0. Longitude must be in range of -180 to 180\u00B0";
        System.out.println(ex.getMessage());
        assertTrue(ex.getMessage().contains(expectedMessage));
    }
}


