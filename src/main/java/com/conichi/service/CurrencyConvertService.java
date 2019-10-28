package com.conichi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conichi.response.CurrencyConversionResponse;

@Service
public class CurrencyConvertService {
	
	@Autowired
	public CurrencyConvertService(CurrencyExchangeRateApiWrapper exchangeRateApiWrapper) {
		this.exchangeRateApiWrapper = exchangeRateApiWrapper;
	}

	public CurrencyExchangeRateApiWrapper exchangeRateApiWrapper;

	public CurrencyConversionResponse getCurrencyExchangeResponse(Double amount, String baseCurrency,
			String targetCurrency) {
		
		//getting conversion rate between base and target currency from the below api wrapper
		
		JSONObject response = exchangeRateApiWrapper.getExchangeBetween(baseCurrency, targetCurrency);
		Double targetcurrencyRate = response.getJSONObject("rates").getDouble(targetCurrency);

		CurrencyConversionResponse conversionResponse = new CurrencyConversionResponse();
		conversionResponse.setBaseAmount(amount);
		conversionResponse.setTargetAmount(amount * targetcurrencyRate);
		conversionResponse.setBaseCurrency(baseCurrency);
		conversionResponse.setTargetCurrency(targetCurrency);
		return conversionResponse;
	}

}
