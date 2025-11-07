package com.ibrahimayhan.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCusstomerIU {

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String tckn;

	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	private Long addressId;
	
	@NotNull
	private Long accountId;
}
