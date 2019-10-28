package com.conichi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)

public class ControllerIntegrationTest {

	@LocalServerPort
	private Integer port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void ControllerIntegrationTest() {

		final ResponseEntity<?> currencyConversionResponse = restTemplate.getForEntity(
				createURLWithPort("/currency/convert?amount=10&baseCurrency=EUR&targetCurrency=INR"), Object.class);
		final ResponseEntity<?> currencyConversionResponseFailure = restTemplate.getForEntity(
				createURLWithPort("/currency/convert?amount=10&baseCurrency=EU&targetCurrency=INR"), Object.class);

		final ResponseEntity<?> vatCountryCodeResponse = restTemplate
				.getForEntity(createURLWithPort("/vat/country?vatNumber=ATU00000024"), Object.class);
		final ResponseEntity<?> vatCountryCodeResponseFailure = restTemplate
				.getForEntity(createURLWithPort("/vat/country?vatNumber="), Object.class);

		assertThat(currencyConversionResponse.getStatusCode(), is(OK));
		assertThat(currencyConversionResponseFailure.getStatusCode(), is(BAD_REQUEST));
		assertThat(vatCountryCodeResponse.getStatusCode(), is(HttpStatus.OK));
		assertThat(vatCountryCodeResponseFailure.getStatusCode(), is(BAD_REQUEST));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/api" + uri;
	}

}
