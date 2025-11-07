package com.ibrahimayhan.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "tckn")
	private String tckn;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private Account account;
	
	
}
