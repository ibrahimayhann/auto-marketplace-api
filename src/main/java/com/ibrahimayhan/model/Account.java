package com.ibrahimayhan.model;

import java.math.BigDecimal;

import com.ibrahimayhan.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity{

	@Column(name = "account_no")
	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;//para gibi hassas işlemlerde bigdewcimal kullanılır
	
	@Column(name = "currency_type")
	@Enumerated(EnumType.STRING) //enumdaki tl ve usd yi string olarak setlemek için kullanılır ordinal olsaydı tl yi 0 usd yi 1 alırdı
	private CurrencyType currencyType;

	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
}
