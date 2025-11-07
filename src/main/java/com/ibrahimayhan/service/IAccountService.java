package com.ibrahimayhan.service;

import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAccountIU;

public interface IAccountService {
	
	public DtoAccount saveAccount(DtoAccountIU input);
	
	public DtoAccount updateAccount(Long accountId,DtoAccountIU input);
	
	public DtoAccount getAccount(Long id);
	
	public RootEntity<String> deleteAccount(Long id);

}
