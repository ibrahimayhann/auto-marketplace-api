package com.ibrahimayhan.config;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.ibrahimayhan.jwt.AuthEntryPoint;
import com.ibrahimayhan.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private static final String AUTHENTICATE="/authenticate";
	private static final String REGISTER="/register";
	private static final String REFRESH_TOKEN="/refreshToken";
	
	
	private static final String[] SWAGGER = {
	        "/swagger-ui/**",
	        "/v3/api-docs/**",
	        "/swagger-ui.html"
	    };
	
	private final JwtAuthenticationFilter jwtFilter;
    private final AuthEntryPoint authEntryPoint;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity )throws Exception{
    	
    	httpSecurity
    	
    		.csrf(csrf->csrf.disable())
    		.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
    	
    		
    		.authorizeHttpRequests(auth->auth
    				.requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN).permitAll()
    				.requestMatchers(SWAGGER).permitAll()
    				.anyRequest().authenticated()
    				)
    		
    		
    		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	
    	return httpSecurity.build();
    }
    

}
