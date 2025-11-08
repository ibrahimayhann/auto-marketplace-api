package com.ibrahimayhan.dto;

import com.ibrahimayhan.model.SaledCar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaleResult {

	private SaledCar saledCar;
	private DtoSaledCar dtoSaledCar;
}
