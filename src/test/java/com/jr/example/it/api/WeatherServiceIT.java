package com.jr.example.it.api;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.jayway.jsonpath.JsonPath;


@ActiveProfiles("integrationtest")
@ComponentScan
@SpringBootTest
@AutoConfigureWebTestClient()
public class WeatherServiceIT {

    @Autowired
    private WebTestClient webTestClient;

    
    @Test
    public void testGetWeather() {

        String requestBody = "{\"latitude\": 57.7072, \"longitude\": 11.9668}";

        EntityExchangeResult<String> responseResult = webTestClient
            .post()
            .uri("/api/weather")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(String.class)
            .returnResult();

        // Verify that the response matches
        String responseBody = responseResult.getResponseBody();
        boolean latitudePresent = JsonPath.parse(responseBody).read("$.latitude") != null;
        boolean longitudePresent = JsonPath.parse(responseBody).read("$.longitude") != null;
        boolean generationtime_msPresent = JsonPath.parse(responseBody).read("$.generationtime_ms") != null;
        assertTrue(latitudePresent);
        assertTrue(longitudePresent);
        assertTrue(generationtime_msPresent);
    }
    
}
