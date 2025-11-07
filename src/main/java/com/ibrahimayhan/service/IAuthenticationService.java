package com.ibrahimayhan.service;

import com.ibrahimayhan.dto.AuthRequest;
import com.ibrahimayhan.dto.AuthResponse;
import com.ibrahimayhan.dto.DtoUser;
import com.ibrahimayhan.dto.RefreshTokenRequest;

public interface IAuthenticationService {
	
	public DtoUser register(AuthRequest authRequest);
	
	public AuthResponse authenticate(AuthRequest authRequest);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
		
	

}
