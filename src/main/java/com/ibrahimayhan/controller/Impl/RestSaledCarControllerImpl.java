package com.ibrahimayhan.controller.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestSaledCarController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoSaledCar;
import com.ibrahimayhan.dto.DtoSaledCarIU;
import com.ibrahimayhan.service.ISaledCarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/saledcar")
public class RestSaledCarControllerImpl implements IRestSaledCarController{
	
	private final ISaledCarService saledCarService;
	
	@PostMapping("/buy")
	@Override
	public RootEntity<DtoSaledCar> buy(@Valid @RequestBody DtoSaledCarIU input) {

		return RootEntity.ok(saledCarService.buy(input));
	}

	
	
}
