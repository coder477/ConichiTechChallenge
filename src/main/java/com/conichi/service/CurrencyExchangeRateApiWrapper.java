package com.conichi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.conichi.exception.BadRequestException;
import com.conichi.exception.ExternalApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyExchangeRateApiWrapper {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private final String CURRENCY_EXCHANGE_API = "https://api.exchangeratesapi.io/latest";

	/**
	 * Getting exchange data from the source API
	 */

	@Cacheable("currency-convert")
	public JSONObject getExchangeBetween(String base, String target) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(CURRENCY_EXCHANGE_API)
				.queryParam("base", base).queryParam("symbols", target);

		try {
			JSONObject resp = new JSONObject(restTemplate.getForObject(uriBuilder.toUriString(), String.class));
			return resp;
		} catch (HttpStatusCodeException httpException) {
			if (httpException.getStatusCode().is4xxClientError()) {
				throw new BadRequestException(httpException.getStatusCode(), httpException.getResponseBodyAsString());
			} else {
				throw new ExternalApiException("Failed calling the data api", httpException.getResponseBodyAsString());
			}
		}
	}
}
