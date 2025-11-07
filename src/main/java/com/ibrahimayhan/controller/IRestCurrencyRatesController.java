package com.ibrahimayhan.controller;


import com.ibrahimayhan.dto.RatesDto;

public interface IRestCurrencyRatesController {
	
	public RootEntity<RatesDto> getCurrencyRates();

}
