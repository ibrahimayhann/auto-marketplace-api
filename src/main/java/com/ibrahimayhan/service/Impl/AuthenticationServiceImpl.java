package com.ibrahimayhan.service.Impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.AuthRequest;
import com.ibrahimayhan.dto.AuthResponse;
import com.ibrahimayhan.dto.DtoUser;
import com.ibrahimayhan.dto.RefreshTokenRequest;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.jwt.JwtService;
import com.ibrahimayhan.model.RefreshToken;
import com.ibrahimayhan.model.User;
import com.ibrahimayhan.repository.RefreshTokenRepository;
import com.ibrahimayhan.repository.UserRepository;
import com.ibrahimayhan.service.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final RefreshTokenRepository refreshTokenRepository;
	
	
	
	@Override
	public DtoUser register(AuthRequest authRequest) {
		if(userRepository.existsByUsername(authRequest.getUsername())) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_IS_ALREADY_EXIST,authRequest.getUsername()));
		}
		else {
			User savedUser= userRepository.save(createUser(authRequest));
			DtoUser dtoUser=new DtoUser();
			BeanUtils.copyProperties(savedUser, dtoUser);
			return dtoUser;
		}
			}
	
	private User createUser(AuthRequest authRequest) {
		
		User user =new User();
		user.setCreateTime(new Date());
		user.setUsername(authRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
		return user;
		
	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {

		try {
			
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			User user=(User) auth.getPrincipal();
			
			String accesToken=jwtService.generateToken(user);
			RefreshToken refreshToken=jwtService.createRefreshToken(user);
			refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accesToken,refreshToken.getRefreshToken());
			
		} catch (Exception e) {
				throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID));
		}
		
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		
		RefreshToken refreshToken=refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
				.orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_INVALID,request.getRefreshToken())));
		
		if(!jwtService.isRefreshTokenValid(refreshToken.getExpiredDATE())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED));
		}
		
		String accesToken=jwtService.generateToken(refreshToken.getUser());
		RefreshToken newRefreshToken=jwtService.createRefreshToken(refreshToken.getUser());
		refreshTokenRepository.save(newRefreshToken);
		return new AuthResponse(accesToken,newRefreshToken.getRefreshToken());
	}
	
	

	
}
