package com.ibrahimayhan.dto;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{
	
	private String firstName;
	
	private String lastName;
	
	private String tckn;

	private Date dateOfBirth;
	
	private DtoAddress address;
	
	private DtoAccount account;

}
