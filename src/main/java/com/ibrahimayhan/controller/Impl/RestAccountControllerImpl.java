package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestAccountController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAccountIU;
import com.ibrahimayhan.service.IAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl implements IRestAccountController {
	
	private final IAccountService accountService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAcccount(@Valid @RequestBody DtoAccountIU input) {
		
		return RootEntity.ok(accountService.saveAccount(input));
	}

	
	
	@PutMapping("update/{accountId}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable(name = "accountId") Long accountId,@Valid @RequestBody DtoAccountIU input) {
		

		return RootEntity.ok(accountService.updateAccount(accountId, input));
	}



	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoAccount> getAccount(@PathVariable(name = "id")	Long id) {
		return RootEntity.ok(accountService.getAccount(id));
	}



	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteAccount(@PathVariable(name = "id") Long id) {
		return accountService.deleteAccount(id);
	}

	
	
	
	
	
	
	
	
	
	

	
}
