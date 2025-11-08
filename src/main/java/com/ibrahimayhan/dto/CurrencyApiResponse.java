package com.ibrahimayhan.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyApiResponse {

    private Map<String, BigDecimal> data;

}
