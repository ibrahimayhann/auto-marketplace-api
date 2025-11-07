package com.ibrahimayhan.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyApiResponse {

    private Map<String, Double> data;

}
