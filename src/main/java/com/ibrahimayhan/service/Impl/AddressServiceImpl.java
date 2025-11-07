package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoAddressIU;
import com.ibrahimayhan.model.Address;
import com.ibrahimayhan.repository.AddressRepository;
import com.ibrahimayhan.service.IAddressService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements IAddressService {
	
	private final AddressRepository addressRepository;
	private final CurrentUserProvider currentUserProvider;
	

	@Override
	public DtoAddress saveAddress(DtoAddressIU input) {

		Address address=new Address();
		BeanUtils.copyProperties(input, address);
		address.setCreateTime(new Date());
		address.setUser(currentUserProvider.getCurrentUser());
		
		Address savedAddress= addressRepository.save(address);
		DtoAddress dtoAddress=new DtoAddress();
		BeanUtils.copyProperties(savedAddress,dtoAddress);
		
		return dtoAddress;
	}

}
