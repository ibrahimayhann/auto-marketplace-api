package com.ibrahimayhan.dto;

import java.time.Instant;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatesDto {

	private String baseCurrency;          // "USD"
    private Map<String, Double> rates;    // { "TRY": .., "EUR": .., ... }
    private Instant fetchedAt;  
}
