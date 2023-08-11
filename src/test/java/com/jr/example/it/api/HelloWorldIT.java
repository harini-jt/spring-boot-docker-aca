package com.jr.example.it.api;

import static org.mockito.BDDMockito.given;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.jr.example.util.FileUtil;

@ActiveProfiles("integrationtest")
@ComponentScan
@SpringBootTest
@AutoConfigureWebTestClient()

public class HelloWorldIT {
	@Autowired
    private WebTestClient webTstClient;

	@MockBean
	private Supplier<Instant> serverTimeSupplier;

	/**
	 * Test api with no input query param.
	 */
	@Test
	public void testHelloWithNoInput(){
		String expected = FileUtil.readFile("/api-resp/expected/no_input.json");
		given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,10,14,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
		webTstClient.get()
				 	.uri("/api/greeting/hello")
				 	.exchange()
				 	.expectStatus().is2xxSuccessful()
				 	.expectBody()
				 	.json(expected, true);
	}

	/**
	 * Test api with  input query param.
	 */
	@Test
	public void testHelloWithQueryParam(){
		String expected = FileUtil.readFile("/api-resp/expected/with_query_input.json");
		given(serverTimeSupplier.get()).willReturn(ZonedDateTime.of(2023,8,10,14,0,0,0, ZoneId.of("CET"))
                                                                .toInstant());
		webTstClient.get()
				 	.uri(uriBuilder -> uriBuilder.path("/api/greeting/hello")
												.queryParam("name","Jithin")
												.build())
				 	.exchange()
				 	.expectStatus().is2xxSuccessful()
				 	.expectBody()
				 	.json(expected, true);
	}



}
