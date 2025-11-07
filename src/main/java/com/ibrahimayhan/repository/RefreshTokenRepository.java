package com.ibrahimayhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrahimayhan.model.RefreshToken;
import java.util.List;
import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	public Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
