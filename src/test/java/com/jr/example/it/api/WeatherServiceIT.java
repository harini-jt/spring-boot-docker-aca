package com.jr.example.it.api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;


@ActiveProfiles("integrationtest")
@ComponentScan
@SpringBootTest
@AutoConfigureWebTestClient()
@Import(WireMockTestConfiguration.class)
public class WeatherServiceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private WireMockServer wireMockServer;
    
    @BeforeTestExecution
    public void setup() {
        // Configure WireMock to respond to your external API's request
        WireMock.configureFor(wireMockServer.port());
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("https://api.open-meteo.com/v1/forecast?latitude=57.7072&longitude=11.9668&hourly=temperature_2m"))
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("weather-response.json")
                // .withBody("{\"latitude\": 57.70521}")
            )
        );
    }

    @AfterTestExecution
    public void teardown() {
        // Stop WireMock server after the test
        wireMockServer.stop();
    }

    @Test
    public void testGetWeather() {
        webTestClient.get()
            .uri("http://localhost:8080/api/weather?latitude=57.70521&longitude=11.973724")
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.latitude").isEqualTo(57.70521)
            .jsonPath("$.longitude").isEqualTo(11.973724);
    }
    
}

/* @SpringBootTest
@AutoConfigureWebTestClient
@Import(WireMockTestConfiguration.class)
public class WeatherServiceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private WireMockServer wireMockServer;

    @Value("${openmeteo.api.url}")
    private String openMeteoApiUrl ="https://api.open-meteo.com/v1"; 
    @BeforeEach
    public void setUp() {
        wireMockServer.stubFor(get(urlEqualTo("/forecast?latitude=57.7072&longitude=11.9668&hourly=temperature_2m"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("weather-response.json")));
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.resetAll();
    }

    @Test
    public void testGetWeatherForecast() {
        webTestClient.get()
                .uri("api/weather/forecast?latitude=57.7072&longitude=11.9668")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.latitude").isEqualTo(57.70521)
                .jsonPath("$.longitude").isEqualTo(11.973724);
        // Add more assertions as needed
    }
} */