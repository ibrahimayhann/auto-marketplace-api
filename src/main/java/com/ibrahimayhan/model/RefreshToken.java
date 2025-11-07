package com.ibrahimayhan.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity{

	@Column(name = "refresh_token")
	private String refreshToken;
	
	@Column(name = "expired_date")
	private Date expiredDATE;
	
	@ManyToOne
	private User user;
	
	
}
