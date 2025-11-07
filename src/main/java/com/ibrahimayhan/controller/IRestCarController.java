package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoCarIU;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarIU input);

}
