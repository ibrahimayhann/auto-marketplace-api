package com.ibrahimayhan.dto;

import java.math.BigDecimal;

import com.ibrahimayhan.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU {

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}
