package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestAddressController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoAddressIU;
import com.ibrahimayhan.service.IAddressService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/address")
@RequiredArgsConstructor
public class RestAddressControllerImpl implements IRestAddressController {
	
	private final IAddressService addressService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress>  saveAddress(@Valid @RequestBody DtoAddressIU input) {

		return RootEntity.ok(addressService.saveAddress(input));
	}

}
