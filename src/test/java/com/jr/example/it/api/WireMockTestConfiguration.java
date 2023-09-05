package com.jr.example.it.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WireMockTestConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(WireMockConfiguration.options().port(8080)); // Adjust the port as needed
    }
}
