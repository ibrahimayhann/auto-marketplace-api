package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.DtoGalleristCar;
import com.ibrahimayhan.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU input);
}
