package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestCarController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoCar;
import com.ibrahimayhan.dto.DtoCarIU;
import com.ibrahimayhan.service.ICarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/car")
@RequiredArgsConstructor
public class RestCarControllerImpl implements IRestCarController{

	private final ICarService carService;

	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU input) {

		return RootEntity.ok(carService.saveCar(input));
	}
	
	
	
	
	
	
	
	
	
	
	
}
