package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoAccount;
import com.ibrahimayhan.dto.DtoAccountIU;
import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoAddressIU;

public interface IRestAddressController {
	
	public RootEntity<DtoAddress>  saveAddress(DtoAddressIU input);
	
	

}
