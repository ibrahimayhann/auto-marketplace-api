package com.ibrahimayhan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

	//id date falan BaseEntityden geliyor zaten
	
	private String city;
	
	private String district;
	
	private String neighborhood;
	
	private String street;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
