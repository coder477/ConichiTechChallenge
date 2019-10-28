package com.conichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.response.CurrencyConversionResponse;
import com.conichi.service.CurrencyConvertService;
import com.conichi.service.CurrencyExchangeRateApiWrapper;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceUnitTest {

	@Test
	public void currencyConversionTest() {

		CurrencyExchangeRateApiWrapper wrapper = mock(CurrencyExchangeRateApiWrapper.class);
		CurrencyConvertService service = new CurrencyConvertService(wrapper);

		CurrencyConversionResponse expectedResponse = new CurrencyConversionResponse();
		expectedResponse.setBaseAmount(1.0);
		expectedResponse.setBaseCurrency("EUR");
		expectedResponse.setTargetCurrency("INR");
		expectedResponse.setTargetAmount(78.7515);

		when(wrapper.getExchangeBetween("EUR", "INR"))
				.thenReturn(new JSONObject("{\"rates\":{\"INR\":78.7515},\"base\":\"EUR\",\"date\":\"2019-10-25\"}"));
		CurrencyConversionResponse response = service.getCurrencyExchangeResponse(1.0, "EUR", "INR");
		assertEquals(expectedResponse, response);
	}

}
