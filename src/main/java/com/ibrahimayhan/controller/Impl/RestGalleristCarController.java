package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestGalleristCarController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoGalleristCar;
import com.ibrahimayhan.dto.DtoGalleristCarIU;
import com.ibrahimayhan.service.IGalleristCarService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/galleristcar")
public class RestGalleristCarController implements IRestGalleristCarController {
	
	
	private final IGalleristCarService galleristCarService;

	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU input) {

		return RootEntity.ok(galleristCarService.saveGalleristCar(input));
	}
	

}
