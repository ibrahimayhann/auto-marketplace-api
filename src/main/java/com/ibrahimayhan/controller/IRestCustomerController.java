package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.DtoCusstomerIU;
import com.ibrahimayhan.dto.DtoCustomer;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCusstomerIU input);

}
