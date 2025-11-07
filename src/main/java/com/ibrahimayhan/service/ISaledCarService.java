package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.DtoSaledCar;
import com.ibrahimayhan.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buy(DtoSaledCarIU input);
}
