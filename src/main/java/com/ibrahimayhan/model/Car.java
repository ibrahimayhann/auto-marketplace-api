package com.ibrahimayhan.model;

import java.math.BigDecimal;

import com.ibrahimayhan.enums.CarStatusType;
import com.ibrahimayhan.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
	
	private String plaka;
	
	private String brand;
	
	private String model;
	
	@Column(name = "produciton_year")
	private Integer productionYear;
	
	private BigDecimal price;
	
	@Column(name = "currency_type")
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;

	@Column(name = "damage_price")
	private BigDecimal damagePrice;
	
	@Column(name = "car_status_type")
	@Enumerated(EnumType.STRING)
	private CarStatusType carStatusType;
	
}
