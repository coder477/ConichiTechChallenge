package com.conichi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;

@Service
public class VATApiWrapper {
	
	//API key used to avail the validate API services

	@Value("${api.cloudmersive.key}")
	private String API_KEY;
	
	/**
	 * Fetching VAT country code and validtaion details from validate API's wrapper
	 */

	@Cacheable("vat-country")
	public VatLookupResponse getVATCountryName(String vatCode) throws ApiException {

		ApiClient defaultClient = Configuration.getDefaultApiClient();

		ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
		Apikey.setApiKey(API_KEY);

		VatApi apiInstance = new VatApi();

		VatLookupRequest input = new VatLookupRequest();
		input.setVatCode(vatCode);
		try {
			VatLookupResponse result = apiInstance.vatVatLookup(input);
			return result;
		} catch (ApiException e) {
			throw new ApiException();
		}

	}

}
