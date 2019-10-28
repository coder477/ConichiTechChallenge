package com.conichi.response;

public class VATCountryResponse {

	String VatNumber;
	String CountryCode;
	Boolean isValid;

	public String getVatNumber() {
		return VatNumber;
	}

	public void setVatNumber(String vatNumber) {
		VatNumber = vatNumber;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CountryCode == null) ? 0 : CountryCode.hashCode());
		result = prime * result + ((VatNumber == null) ? 0 : VatNumber.hashCode());
		result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VATCountryResponse other = (VATCountryResponse) obj;
		if (CountryCode == null) {
			if (other.CountryCode != null)
				return false;
		} else if (!CountryCode.equals(other.CountryCode))
			return false;
		if (VatNumber == null) {
			if (other.VatNumber != null)
				return false;
		} else if (!VatNumber.equals(other.VatNumber))
			return false;
		if (isValid == null) {
			if (other.isValid != null)
				return false;
		} else if (!isValid.equals(other.isValid))
			return false;
		return true;
	}

}
