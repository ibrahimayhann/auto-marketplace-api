package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.DtoAddress;
import com.ibrahimayhan.dto.DtoAddressIU;

public interface IAddressService {

	public  DtoAddress saveAddress(DtoAddressIU input);
}
