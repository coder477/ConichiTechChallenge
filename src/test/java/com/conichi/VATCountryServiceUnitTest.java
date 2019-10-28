package com.conichi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.model.VatLookupResponse;
import com.conichi.exception.InvalidVATCodeException;
import com.conichi.response.CurrencyConversionResponse;
import com.conichi.response.VATCountryResponse;
import com.conichi.service.VATApiWrapper;
import com.conichi.service.VATCountryService;

@RunWith(MockitoJUnitRunner.class)
public class VATCountryServiceUnitTest {

	@Test
	public void currencyConversionTest() throws ApiException, InvalidVATCodeException {

		VATApiWrapper wrapper = mock(VATApiWrapper.class);
		VATCountryService service = new VATCountryService(wrapper);

		VATCountryResponse expectedResponse = new VATCountryResponse();
		expectedResponse.setCountryCode("AT");
		expectedResponse.setVatNumber("ATU00000024");
		expectedResponse.setIsValid(false);

		// mocking up the api response
		VatLookupResponse apiResponse = new VatLookupResponse();
		apiResponse.setBusinessAddress("");
		apiResponse.setBusinessName("");
		apiResponse.setCountryCode("AT");
		apiResponse.setIsValid(false);
		apiResponse.setVatNumber("ATU00000024");

		when(wrapper.getVATCountryName("ATU00000024")).thenReturn(apiResponse);

		VATCountryResponse response = service.getVATCountry("ATU00000024");

		assertEquals(expectedResponse, response);
	}

}
