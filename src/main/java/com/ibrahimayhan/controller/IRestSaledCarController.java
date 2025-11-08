package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoSaledCar;
import com.ibrahimayhan.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar>  buy(DtoSaledCarIU input);
}
