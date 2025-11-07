package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestCustomerController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoCusstomerIU;
import com.ibrahimayhan.dto.DtoCustomer;
import com.ibrahimayhan.service.ICustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl implements IRestCustomerController{
	
	private final ICustomerService customerService;
	
	
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCusstomerIU input) {
		
		return RootEntity.ok(customerService.saveCustomer(input));
	}

	
	
	
	
	
	
	
}
