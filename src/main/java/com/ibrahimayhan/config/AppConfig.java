package com.ibrahimayhan.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;
import com.ibrahimayhan.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	private final UserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return username->userRepository.findByUsername(username)
				.orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.USERNAME_IS_NOT_FOUND,username)));
	} 
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg)throws Exception{
		return cfg.getAuthenticationManager();
	}
	
	
}
