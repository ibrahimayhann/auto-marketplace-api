package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestCurrencyRatesController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.RatesDto;
import com.ibrahimayhan.service.ICurrencyRateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("rest/api/currency")
@RequiredArgsConstructor
public class CurrencyRatesController implements IRestCurrencyRatesController {
	
	private final ICurrencyRateService currencyRateService;
	
	@GetMapping("/rates")
	@Override
	public RootEntity<RatesDto> getCurrencyRates() {

		return RootEntity.ok(currencyRateService.getAllRates());
	}

	
}
