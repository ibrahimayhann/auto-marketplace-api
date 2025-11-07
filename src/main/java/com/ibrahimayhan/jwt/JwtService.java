package com.ibrahimayhan.jwt;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ibrahimayhan.model.RefreshToken;
import com.ibrahimayhan.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public static final String SECRET_KEY="O0b3MZ51xhOeMvM2kl57s7LNCzQp3uI3yb6hV4DQey0=";
	
	private static final Duration REFRESH_TTL=Duration.ofHours(4);

	
	public Key getKey() {
		byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
		
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	
	public String generateToken(UserDetails userDetails) {
		
	return	Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+2*1000*60*60))
		.signWith(getKey(),SignatureAlgorithm.HS256)
		.compact();
	}
		
	
	
	public Claims parseClaims(String token) {
		
		Claims claims= Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
		
		return claims;
		
	}
	
	
	private <T> T exportToken(String token,Function<Claims, T> claimsFunction) {
		
		Claims claims=parseClaims(token);
		return claimsFunction.apply(claims);
	}
	
	
	public String getUsernameByToken(String token) {
		return exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenValid(String token) {
		
		Date expiredDate= exportToken(token, Claims::getExpiration);
		return new Date().before(expiredDate);
	}
	
	public Object getClaimsByKey(String token,String key) {
		
		Claims claims=parseClaims(token);
		
		return claims.get(key);
			
	}
	
	
	public RefreshToken createRefreshToken(User user) {
		
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setUser(user);
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDATE(Date.from(Instant.now().plus(REFRESH_TTL)));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		return refreshToken;
	}
	
	public boolean isRefreshTokenValid(Date expireDate) {
		
		return new Date().before(expireDate);
	}
	
	
	
	
}
