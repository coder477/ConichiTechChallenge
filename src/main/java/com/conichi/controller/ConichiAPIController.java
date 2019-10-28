package com.conichi.controller;

import static org.slf4j.LoggerFactory.getLogger;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudmersive.client.invoker.ApiException;
import com.conichi.exception.BadRequestException;
import com.conichi.exception.ExternalApiException;
import com.conichi.exception.InvalidVATCodeException;
import com.conichi.response.CurrencyConversionResponse;
import com.conichi.response.ErrorReponse;
import com.conichi.response.VATCountryResponse;
import com.conichi.service.CurrencyConvertService;
import com.conichi.service.VATCountryService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/api")
@EnableSwagger2
public class ConichiAPIController {

	@Autowired
	private CurrencyConvertService conversionService;

	@Autowired
	private VATCountryService vatCountryService;

	private static final Logger log = getLogger(CurrencyConvertService.class);

	/**
	 * Service to fetch and return current time 
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/time")
	public ResponseEntity getTime() {

		return ResponseEntity.ok(new HashMap<String, Date>() {
			{
				put("currentTime", new Date());
			}
		});
	}
	
	/**
	 * Service to find country code based on the vat number given
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/vat/country")
	public ResponseEntity getVATCountry(@RequestParam String vatNumber)
			throws ParseException, ApiException, InvalidVATCodeException {
		VATCountryResponse response = vatCountryService.getVATCountry(vatNumber);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Service to find currency conversion of a given amount based on a base and target currency
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/currency/convert")
	public ResponseEntity convertCurrency(@RequestParam Double amount, @RequestParam String baseCurrency,
			@RequestParam String targetCurrency) throws ParseException {
		CurrencyConversionResponse response = conversionService.getCurrencyExchangeResponse(amount, baseCurrency,
				targetCurrency);
		return ResponseEntity.ok(response);
	}

	/**
	 * Exception handlers for this controller are added below.
	 */

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity handleBadRequestException(BadRequestException exception) {
		log.error("Exception while processing request", exception);
		return ResponseEntity.badRequest().body(new ErrorReponse(exception.getValue().value(),
				"Please verify the given input request.", exception.getMessage()));
	}

	@ExceptionHandler(InvalidVATCodeException.class)
	public ResponseEntity handleInvalidVATCodeException(InvalidVATCodeException exception) {
		log.error("Exception while processing request", exception);
		return ResponseEntity.badRequest().body(
				new ErrorReponse(exception.getValue().value(), "Given VAT number is invalid", exception.getMessage()));
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity handleApiException(ApiException exception) {
		log.error("Exception while processing request", exception);
		return ResponseEntity.badRequest().body(new ErrorReponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Somthing went Wrong  While fetching the requested details.", exception.getMessage()));
	}

	@ExceptionHandler(ExternalApiException.class)
	public ResponseEntity handleExternalApiError(ExternalApiException exception) {
		log.error("Exception while processing request", exception);
		return ResponseEntity.badRequest().body(new ErrorReponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Somthing went Wrong  While fetching the requested details.", exception.getValue()));
	}
}
