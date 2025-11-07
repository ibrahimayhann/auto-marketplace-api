package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoCusstomerIU;
import com.ibrahimayhan.dto.DtoCustomer;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.model.Account;
import com.ibrahimayhan.model.Address;
import com.ibrahimayhan.model.Customer;
import com.ibrahimayhan.repository.AccountRepository;
import com.ibrahimayhan.repository.AddressRepository;
import com.ibrahimayhan.repository.CustomerRepository;
import com.ibrahimayhan.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{
	
	private final CustomerRepository customerRepository;
	private final AddressRepository addressRepository;
	private final AccountRepository accountRepository;

	
	@Override
	public DtoCustomer saveCustomer(DtoCusstomerIU input) {

		Customer customer=new Customer();
		BeanUtils.copyProperties(input, customer);
		customer.setCreateTime(new Date());
		
		
		Address address=addressRepository.findById(input.getAddressId()).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.ADDRESS_ID_IS_NOT_FIND)));
		customer.setAddress(address);

		
		Account account=accountRepository.findById(input.getAccountId()).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.Account_ID_IS_NOT_FIND)));
		customer.setAccount(account);
		
		Customer savedCustomer =customerRepository.save(customer);
		
		
		DtoCustomer dtoCustomer=new DtoCustomer();
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		

		DtoAddress dtoAddress=new DtoAddress();
		BeanUtils.copyProperties(address, dtoAddress);
		dtoCustomer.setAddress(dtoAddress);
		
		
		DtoAccount dtoAccount=new DtoAccount();
		BeanUtils.copyProperties(account, dtoAccount);
		dtoCustomer.setAccount(dtoAccount);

		return dtoCustomer;
	}

	
	
	
	
	
}
