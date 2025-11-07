package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoGallerist;
import com.ibrahimayhan.dto.DtoGalleristIU;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.model.Address;
import com.ibrahimayhan.model.Gallerist;
import com.ibrahimayhan.repository.AddressRepository;
import com.ibrahimayhan.repository.GalleristRepository;
import com.ibrahimayhan.service.IGalleristService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleristServiceImpl implements IGalleristService{
	
	private final GalleristRepository galleristRepository;
	private final AddressRepository addressRepository;
	
	
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU input) {
		Gallerist gallerist=new Gallerist();
		BeanUtils.copyProperties(input, gallerist);
		gallerist.setCreateTime(new Date());
		
		Address address=addressRepository.findById(input.getAddressId()).orElseThrow(()->new BaseException(new ErrorMessage(MessageType.ADDRESS_ID_IS_NOT_FIND)));
		gallerist.setAddress(address);
		
		Gallerist savedGallerist=galleristRepository.save(gallerist);
		DtoGallerist dtoGallerist=new DtoGallerist();
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		
		DtoAddress dtoAddress=new DtoAddress();
		BeanUtils.copyProperties(address, dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		
		return dtoGallerist;
	}
	
	
	

}
