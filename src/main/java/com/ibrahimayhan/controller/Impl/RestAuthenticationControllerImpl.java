package com.ibrahimayhan.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IRestAuthenticationControlller;
import com.ibrahimayhan.controller.RestBaseController;
import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.dto.AuthRequest;
import com.ibrahimayhan.dto.AuthResponse;
import com.ibrahimayhan.dto.DtoUser;
import com.ibrahimayhan.dto.RefreshTokenRequest;
import com.ibrahimayhan.service.IAuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationControlller{

	private final IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {

		return ok(authenticationService.register(authRequest)) ;
	}

	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
		// TODO Auto-generated method stub
		return ok(authenticationService.authenticate(authRequest));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
		// TODO Auto-generated method stub
		return ok(authenticationService.refreshToken(request));
	}

	
}
