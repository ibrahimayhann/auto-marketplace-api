package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAccountIU;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.model.Account;
import com.ibrahimayhan.repository.AccountRepository;
import com.ibrahimayhan.service.IAccountService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService{
	
	private final AccountRepository accountRepository;
	private final CurrentUserProvider currentUserProvider;
	
	
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU input) {
		
		Account account=new Account();
		BeanUtils.copyProperties(input, account);
		account.setCreateTime(new Date());
		account.setUser(currentUserProvider.getCurrentUser());
		
		Account savedAccount=accountRepository.save(account);
		
		DtoAccount dtoAccount=new DtoAccount();
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		return dtoAccount;
	}


	//@Transactional
	@Override
	public DtoAccount updateAccount(Long accountId,DtoAccountIU input) {
		
		
		Account account=accountRepository.findById(accountId).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.ACCOUNT_IS_NOT_FIND)));
		BeanUtils.copyProperties(input, account);
		
		Account updatedAccount= accountRepository.save(account);
		DtoAccount dtoAccount=new DtoAccount();
		BeanUtils.copyProperties(updatedAccount,dtoAccount );
		
		return dtoAccount;
	
		
		
		
	}


	@Override
	public DtoAccount getAccount(Long id) {

		Account account=accountRepository.findById(id).orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.ACCOUNT_IS_NOT_FIND)));
		
		DtoAccount dtoAccount =new DtoAccount();
		BeanUtils.copyProperties(account,dtoAccount);
		return dtoAccount;
	}


	@Override
	public RootEntity<String> deleteAccount(Long id) {
		
		Account account= accountRepository.findById(id).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.ACCOUNT_IS_NOT_FIND)));
		accountRepository.deleteById(id);
		return RootEntity.ok("Account başarıyla silindi , ID:"+id);
	}
	
	
	
	

}
