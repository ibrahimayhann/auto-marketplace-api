package com.ibrahimayhan.controller;

import com.ibrahimayhan.dto.AuthRequest;
import com.ibrahimayhan.dto.AuthResponse;
import com.ibrahimayhan.dto.DtoUser;
import com.ibrahimayhan.dto.RefreshTokenRequest;

public interface IRestAuthenticationControlller {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
	
	public RootEntity<AuthResponse>  authenticate(AuthRequest authRequest);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
		
}
