package com.ibrahimayhan.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatesDto {

	private String baseCurrency;          // "USD"
    private Map<String, BigDecimal> rates;    // { "TRY": .., "EUR": .., ... }
    private Instant fetchedAt;  
}
