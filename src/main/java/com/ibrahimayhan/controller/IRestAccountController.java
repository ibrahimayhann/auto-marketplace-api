package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAccountIU;

public interface IRestAccountController {
	
	public RootEntity<DtoAccount> saveAcccount(DtoAccountIU input);
	
	public RootEntity<DtoAccount> updateAccount(Long accountId,DtoAccountIU input);
	
	public RootEntity<DtoAccount> getAccount(Long id);
	
	public RootEntity<String> deleteAccount(Long id);



}
