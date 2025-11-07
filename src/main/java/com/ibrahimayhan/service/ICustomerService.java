package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.DtoCusstomerIU;
import com.ibrahimayhan.dto.DtoCustomer;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCusstomerIU input);
}
