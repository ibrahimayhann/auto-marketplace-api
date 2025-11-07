package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestGalleristController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoGallerist;
import com.ibrahimayhan.dto.DtoGalleristIU;
import com.ibrahimayhan.service.IGalleristService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl implements IRestGalleristController {

	private final IGalleristService galleristService;

	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> savegallerist(@Valid @RequestBody DtoGalleristIU input) {

		return RootEntity.ok(galleristService.saveGallerist(input));
	}
	
	
	
	
	
	
	
}
