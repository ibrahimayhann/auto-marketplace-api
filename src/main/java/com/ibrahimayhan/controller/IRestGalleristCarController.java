package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoGalleristCar;
import com.ibrahimayhan.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {
	
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU input);

}
