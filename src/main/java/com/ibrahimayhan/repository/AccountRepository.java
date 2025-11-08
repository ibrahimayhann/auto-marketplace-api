package com.ibrahimayhan.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibrahimayhan.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByUserId(Long userId);
	@Modifying
    @Query("UPDATE Account a SET a.amount = :amount WHERE a.id = :id")
    int updateAmountById(@Param("id") Long id, @Param("amount") BigDecimal amount);

}
