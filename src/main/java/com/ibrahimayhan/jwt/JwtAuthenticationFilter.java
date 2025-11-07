package com.ibrahimayhan.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ibrahimayhan.controller.RootEntity;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final AuthEntryPoint authEntryPoint;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header;
		String token;
		String username;
		
		header=request.getHeader("Authorization");
		if(header==null) {
			filterChain.doFilter(request, response);
			return;//chate sor handle edilmeli mi
		}
		token=header.substring(7);// bunu try dahil etmeli miyim
		
		try {
			username=jwtService.getUsernameByToken(token);
			
			if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
				
				UserDetails userDetails=userDetailsService.loadUserByUsername(username);
				if(userDetails!=null&&jwtService.isTokenValid(token)) {
					UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities()); 
					authentication.setDetails(userDetails);
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				
			}
			
			
			
			
			
		} catch (ExpiredJwtException ex) {
			
			//throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPİRED,ex.getMessage()));
			authEntryPoint.commence(
	                request, response,
	                new org.springframework.security.authentication.InsufficientAuthenticationException("JWT expired", ex));
	            return; // JSON yazıldı, zinciri bitir
	            
	            
	            
	            
	            
		}catch(JwtException |IllegalArgumentException ex) {
			
			
			
			//throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION,e.getMessage()));
			authEntryPoint.commence(
	                request, response,
	                new org.springframework.security.authentication.BadCredentialsException("Invalid JWT", ex));
	            return;

		}
		
		
		
		
		
		
		
		filterChain.doFilter(request, response);
		
	}
	
	
	

}
