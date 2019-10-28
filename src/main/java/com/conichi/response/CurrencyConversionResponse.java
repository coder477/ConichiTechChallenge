package com.conichi.response;

public class CurrencyConversionResponse {

	String baseCurrency;
	String targetCurrency;
	Double baseAmount;
	Double targetAmount;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public Double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseAmount == null) ? 0 : baseAmount.hashCode());
		result = prime * result + ((baseCurrency == null) ? 0 : baseCurrency.hashCode());
		result = prime * result + ((targetAmount == null) ? 0 : targetAmount.hashCode());
		result = prime * result + ((targetCurrency == null) ? 0 : targetCurrency.hashCode());
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
		CurrencyConversionResponse other = (CurrencyConversionResponse) obj;
		if (baseAmount == null) {
			if (other.baseAmount != null)
				return false;
		} else if (!baseAmount.equals(other.baseAmount))
			return false;
		if (baseCurrency == null) {
			if (other.baseCurrency != null)
				return false;
		} else if (!baseCurrency.equals(other.baseCurrency))
			return false;
		if (targetAmount == null) {
			if (other.targetAmount != null)
				return false;
		} else if (!targetAmount.equals(other.targetAmount))
			return false;
		if (targetCurrency == null) {
			if (other.targetCurrency != null)
				return false;
		} else if (!targetCurrency.equals(other.targetCurrency))
			return false;
		return true;
	}
}
