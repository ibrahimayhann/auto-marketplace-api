package com.ibrahimayhan.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004","Kayıt bulunamadı."),
	TOKEN_IS_EXPİRED("1005","Token süresi dolmuş:"),
	USERNAME_IS_NOT_FOUND("1006","Kullanıcı bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007","Username veya password hatalı"),
	USERNAME_IS_ALREADY_EXIST("1007","Böyle bir kullanıcı zaten var"),
	REFRESH_TOKEN_IS_EXPIRED("1008","Refresh token süresi dolmuş"),
	REFRESH_TOKEN_IS_INVALID("1009","Hatalı RefreshToken"),
	ACCOUNT_IS_NOT_FIND("1010","Account bulunamadı"),
	ADDRESS_ID_IS_NOT_FIND("1011","Girilen adres_id ile kayıtlı bir adres bulunamadı"),
	Account_ID_IS_NOT_FIND("1012","Girilen account_id ile kayıtlı bir account bulunamadı"),
	AMOUNT_IS_NO_ENOUGH("1014","Bakiye yetersiz"),
	CURRENCY_RATES_IS_OCCURED("1013","Kur verisi alınamadı"),

	GENERAL_EXCEPTION("9999","Genel bir hata oluştu.");
	
	private String code;
	
	private String message;
	
	private MessageType(String code,String message) {
		
		this.code=code;
		this.message=message;
		// TODO Auto-generated constructor stub
	}
}
