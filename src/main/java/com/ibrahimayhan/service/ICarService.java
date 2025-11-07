package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoCarIU;

public interface ICarService {
	
	public DtoCar saveCar(DtoCarIU input);

}
