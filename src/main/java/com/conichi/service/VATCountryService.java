package com.conichi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.model.VatLookupResponse;
import com.conichi.exception.InvalidVATCodeException;
import com.conichi.response.VATCountryResponse;

@Service
public class VATCountryService {

	@Autowired
	public VATApiWrapper vatApiWrapper;

	public VATCountryService(VATApiWrapper vatApiWrapper) {
		this.vatApiWrapper=vatApiWrapper;
	}

	public VATCountryResponse getVATCountry(String vatCode) throws ApiException, InvalidVATCodeException {
		if (vatCode.trim().length() == 0 || vatCode == null) {
			throw new InvalidVATCodeException(HttpStatus.BAD_REQUEST, "Given input is not valid");
		}
		
		//getting country code from vat number from the below api wrapper

		VatLookupResponse response = vatApiWrapper.getVATCountryName(vatCode);

		VATCountryResponse vatCountryResponse = new VATCountryResponse();
		vatCountryResponse.setCountryCode(response.getCountryCode());
		vatCountryResponse.setVatNumber(response.getVatNumber());
		vatCountryResponse.setIsValid(response.isIsValid());
		return vatCountryResponse;

	}

}
